/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package lpoo.geom;

import java.util.*;

public class Quadtree<P extends Point2> implements Iterable<Quadtree.NodeData<P>> {

  public static class NodeData<P extends Point2> implements Iterable<P> {
    public final Bounds2 bounds() {
      return new Bounds2(bounds);
    }
  
    public final boolean contains(Point2 p) {
      return bounds.contains(p);
    }

    public final boolean isEmpty() {
      return points.isEmpty();
    }

    @Override
    public final Iterator<P> iterator() {
      return points.iterator();
    }
  
    public final int pointCount() {
      return points.size();
    }
    
    public final int depth() {
      return depth;
    }
  
    protected final Bounds2 bounds;
    protected final LinkedList<P> points = new LinkedList<>();
    protected final int depth;
  
    protected NodeData(final Bounds2 bounds) {
      this.bounds = bounds;
      depth = 0;
    }
  
    protected NodeData(Point2 p, Point2 size, int depth) {
      bounds = new Bounds2(p, size);
      this.depth = depth;
    }
  } 
  
  public final int pointsPerNode;

  public Quadtree(final P[] points, int pointsPerNode) {
    this(bounds(points), pointsPerNode);
    for (P p : points)
      root.add(p);
    split(root);
  }

  public final Bounds2 bounds() {
    return root.bounds();
  }

  public final int pointCount() {
    return pointCount(root);
  }

  public final int size() {
    return nodeCount;
  }

  public final int leafCount() {
    return leafCount;
  }

  @Override
  public Iterator<NodeData<P>> iterator() {
    return new QuadtreeLeafIterator<>(root);
  }

  public static final float fatFactor = 1.01f;
  public static final int minPointsPerNode = 5;
  public static final int maxDepth = 8;

  static class Node<P extends Point2> extends NodeData<P> {
    Node(final Bounds2 bounds) {
      super(bounds);
    }

    Node(Point2 p, Point2 size, int depth) {
      super(p, size, depth);
    }

    final boolean isLeaf() {
      return children == null;
    }

    final void clear() {
      points.clear();
    }

    final void add(P p) {
      points.add(p);
    }

    Node<P>[] children;
  } 

  private Node<P> root;
  private int nodeCount;
  private int leafCount;

  private Quadtree(final Bounds2 bounds, int pointsPerNode) {
    this.pointsPerNode = Math.max(minPointsPerNode, pointsPerNode);
    root = new Node<>(new Bounds2(bounds).inflate(fatFactor));
    nodeCount = 1;
  }

  private void split(Node<P> node) {
    if (node.pointCount() <= pointsPerNode || node.depth == maxDepth)
      return;
    {
      Point2 p = node.bounds.p1();
      Point2 s = node.bounds.size().mul(0.5f);
      int d = node.depth + 1;

      @SuppressWarnings("unchecked")
      Node<P>[] newChildren = (Node<P>[]) new Node[4];
      node.children = newChildren;

      node.children[0] = new Node<>(p, s, d);
      node.children[1] = new Node<>(new Point2(p.x + s.x, p.y), s, d);
      node.children[2] = new Node<>(new Point2(p.x + s.x, p.y + s.y), s, d);
      node.children[3] = new Node<>(new Point2(p.x, p.y + s.y), s, d);
      leafCount += 3;
      nodeCount += 4;
    }
    for (P p : node)
      for (int i = 0; i < 4; i++)
        if (node.children[i].contains(p)) {
          node.children[i].add(p);
          break;
        }
    node.clear();
    for (int i = 0; i < 4; i++)
      split(node.children[i]);
  }

  /**
   * Calcula a distância mínima de um ponto até a borda de uma Bounding Box.
   * Utilizado para otimizar as buscas e podar quadrantes da árvore.
   */
  private static float distanceToAABB(Bounds2 b, Point2 p) {
    Point2 min = b.p1();
    Point2 max = b.p2();
    float dx = Math.max(min.x - p.x, Math.max(0, p.x - max.x));
    float dy = Math.max(min.y - p.y, Math.max(0, p.y - max.y));
    return (float) Math.sqrt(dx * dx + dy * dy);
  }

  public KNN<P> findNeighbors(P point, int k, PointFunc<P> filter) {
    KNN<P> knn = new KNN<>(k);
    findNeighborsRecursive(root, point, filter, knn);
    return knn;
  }

  private void findNeighborsRecursive(Node<P> node, P point, PointFunc<P> filter, KNN<P> knn) {
    // Poda da árvore: Se a caixa mais próxima for mais distante que o vizinho 
    // mais distante já encontrado (e o KNN estiver cheio), ignora este quadrante inteiro.
    if (knn.isFull() && distanceToAABB(node.bounds, point) > knn.getMaxDistance()) {
        return;
    }

    if (node.isLeaf()) {
        for (P p : node) {
            if (filter == null || filter.run(p)) {
                knn.add(p, Point2.distance(p, point));
            }
        }
    } else {
        for (int i = 0; i < 4; i++) {
            findNeighborsRecursive(node.children[i], point, filter, knn);
        }
    }
  }

  public long forEachNeighbor(P point, float radius, PointFunc<P> f, PointFunc<P> filter) {
    long[] processCount = {0}; // Usando array para passar por referência
    forEachNeighborRecursive(root, point, radius, f, filter, processCount);
    return processCount[0];
  }

  private boolean forEachNeighborRecursive(Node<P> node, P point, float radius, PointFunc<P> f, PointFunc<P> filter, long[] count) {
    // Poda: Se a caixa está fora do raio de busca, descarta o quadrante.
    if (distanceToAABB(node.bounds, point) > radius) {
        return true; 
    }

    if (node.isLeaf()) {
        for (P p : node) {
            if (Point2.distance(p, point) <= radius) {
                if (filter == null || filter.run(p)) {
                    count[0]++;
                    if (!f.run(p)) return false; // Para a busca se f devolver false
                }
            }
        }
    } else {
        for (int i = 0; i < 4; i++) {
            if (!forEachNeighborRecursive(node.children[i], point, radius, f, filter, count)) {
                return false;
            }
        }
    }
    return true;
  }

  private static <P extends Point2> Bounds2 bounds(final P[] points) {
    Bounds2 bounds = new Bounds2();
    for (P p : points)
      bounds.inflate(p);
    return bounds;
  }

  private static <P extends Point2> int pointCount(Node<P> node) {
    if (node.isLeaf()) return node.pointCount();
    int count = 0;
    for (int i = 0; i < 4; i++) count += pointCount(node.children[i]);
    return count;
  }
} 

final class QuadtreeLeafIterator<P extends Point2> implements Iterator<Quadtree.NodeData<P>> {
  private Stack<Quadtree.Node<P>> stack;

  QuadtreeLeafIterator(Quadtree.Node<P> root) {
    stack = new Stack<>();
    stack.push(root);
  }

  @Override
  public boolean hasNext() {
    return !stack.empty();
  }

  @Override
  public Quadtree.NodeData<P> next() {
    Quadtree.Node<P> node = stack.pop();
    if (!node.isLeaf())
      for (int i = 4; i > 0;)
        stack.push(node.children[--i]);
    return node;
  }
}