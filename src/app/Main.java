/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package app;

import java.io.*;
import java.util.*;
import lpoo.geom.*;

public class Main {
    
  static int toInteger(String s) {
    return Integer.parseInt(s);
  }

  static float toFloat(String s) {
    return Float.parseFloat(s);
  }

  static Particle2[] readParticlesFromFile(String filename) throws IOException {
    try (BufferedReader is = new BufferedReader(new FileReader(filename))) {
      StringTokenizer t = new StringTokenizer(is.readLine());
      Point2 s = new Point2(toFloat(t.nextToken()), toFloat(t.nextToken()));
      s.print("Domain size: ");

      int n = toInteger(is.readLine());
      Particle2[] points = new Particle2[n];
      Random rand = new Random();

      for (int i = 0; i < n; i++) {
        t = new StringTokenizer(is.readLine());
        int colorType = rand.nextInt(3);
        int r = colorType == 0 ? 255 : 0;
        int g = colorType == 1 ? 255 : 0;
        int b = colorType == 2 ? 255 : 0;
        
        points[i] = new Particle2(toFloat(t.nextToken()), toFloat(t.nextToken()), r, g, b);
      }
      System.out.printf("%d particulas (com RGB) lidas com sucesso.\n", n);
      return points;
    }
  }

  static void printKNNResults(Particle2 alvo, KNN<Particle2> resultados) {
    Console.printf("\n--- Resultados do KNN ---\n");
    Console.printf("Alvo: %s\n", alvo.toString());
    Console.printf("Vizinhos encontrados: %d\n", resultados.getCount());
    
    for (int i = 0; i < resultados.getCount(); i++) {
        KNN<Particle2>.Pair par = resultados.getPair(i);
        Console.printf("  %d) Distancia: %.2f | Particula: %s\n", (i+1), par.distance, par.point.toString());
    }
    Console.printf("-------------------------\n");
  }

  public static void main(String[] args) {
    try {
      String fn = args.length == 0 ? Console.readString("Nome do Arquivo (ex: points.txt)") : args[0];
      
      Particle2[] particles = readParticlesFromFile(fn);
      Quadtree<Particle2> qtree = new Quadtree<>(particles, 5);

      qtree.bounds().print("Quadtree bounds: ");
      System.out.printf("Nodes: %d\nLeaf nodes: %d\n\n", qtree.size(), qtree.leafCount());
      
      // Inicia o visualizador gráfico original adaptado
      new QuadtreeViewer(qtree);

      // Expressão Lambda instanciada uma única vez para uso em ambas as buscas
      PointFunc<Particle2> filtroCorVermelha = (p) -> p.r == 255;

      boolean running = true;
      while (running) {
          Console.println("\n*** MENU QUADTREE ***");
          Console.println("[1] Buscar Vizinhos (KNN) - Filtro: Vermelhos");
          Console.println("[2] Buscar Vizinhos por Raio - Filtro: Vermelhos");
          Console.println("[x] Sair");
          
          char opt = Console.readOption("Escolha uma opcao", "12x");
          
          if (opt == 'x') {
              running = false;
              continue;
          }

          int index = Console.readInt("Digite o indice da particula alvo (0 a " + (particles.length - 1) + ")");
          if (index < 0 || index >= particles.length) {
              Console.println("Indice invalido!");
              continue;
          }
          Particle2 alvo = particles[index];

          if (opt == '1') {
              int k = Console.readInt("Quantos vizinhos (K)?");
            
              KNN<Particle2> knn = qtree.findNeighbors(alvo, k, filtroCorVermelha);
              printKNNResults(alvo, knn);
          } 
          else if (opt == '2') {
              float raio = Console.readFloat("Digite o raio de busca");
              
              PointFunc<Particle2> printPoint = (p) -> {
                  Console.printf("  Encontrado no raio: %s\n", p.toString());
                  return true; 
              };

              Console.printf("\n--- Busca por Raio (Raio: %.2f) ---\n", raio);
              Console.printf("Alvo: %s\n", alvo.toString());
              
              long qtd = qtree.forEachNeighbor(alvo, raio, printPoint, filtroCorVermelha);
              Console.printf("Total de particulas vermelhas encontradas: %d\n", qtd);
              Console.printf("-----------------------------------\n");
          }
      }
    } catch (Exception e) {
      System.out.println("Erro critico: " + e.getMessage());
    }
  }
}