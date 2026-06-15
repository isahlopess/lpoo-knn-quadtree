/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package lpoo.geom;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KNN<P extends Point2> {
    
    /**
     * Classe interna exigida pelo roteiro para representar o par (Ponto, Distância)
     */
    public class Pair {
        public P point;
        public float distance;

        public Pair(P point, float distance) {
            this.point = point;
            this.distance = distance;
        }
    }

    private int k;
    private List<Pair> neighbors;

    public KNN(int k) {
        this.k = k;
        this.neighbors = new ArrayList<>(k);
    }

    /**
     * Tenta adicionar um ponto à lista de vizinhos.
     * Garante que a lista se mantenha ordenada da menor para a maior distância.
     */
    public void add(P point, float distance) {
        neighbors.add(new Pair(point, distance));
        
        // Ordena a lista com base na distância (Exigência do roteiro)
        neighbors.sort(Comparator.comparingDouble(p -> p.distance));
        
        // Se a lista exceder o número K máximo, removemos o elemento mais distante (o último)
        if (neighbors.size() > k) {
            neighbors.remove(neighbors.size() - 1);
        }
    }

    /**
     * Retorna a distância do vizinho mais distante atualmente na lista.
     * Usado pela Quadtree para otimizar e descartar quadrantes inteiros na busca.
     */
    public float getMaxDistance() {
        if (neighbors.isEmpty()) {
            return Float.POSITIVE_INFINITY;
        }
        return neighbors.get(neighbors.size() - 1).distance;
    }

    public boolean isFull() {
        return neighbors.size() == k;
    }

    // --- Métodos de acesso para iterar sobre os resultados no Main ---

    public int getCount() {
        return neighbors.size();
    }

    public Pair getPair(int index) {
        return neighbors.get(index);
    }
}