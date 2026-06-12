/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package app;

import app.engine.Game;
import app.engine.GameObject;
import app.engine.Scene;
import app.components.Camera;
import app.components.Light;
import app.components.Mesh;
import app.utils.Console;

import java.io.IOException;

public class Main {
    
    public static void main(String[] args) {
        
        // Inicializa o motor de jogo
        Game game = new Game();

        // Obtém a cena padrão que é criada automaticamente com o Game
        Scene mainScene = game.getScene("Main Scene");

        // Cria o jogo "padrão" com os 3 objetos exigidos no roteiro.
        setupStandardGame(mainScene);

        Console.printf("========================================\n");
        Console.printf("      MOTOR DE JOGOS LPOO INICIADO      \n");
        Console.printf("========================================\n");

        // Laço interativo da interface em modo texto
        boolean running = true;
        while (running) {
            Console.printf("\n** Menu Principal do Motor (SEE THE CODE):\n");
            Console.printf("[1] Exibir arvore do jogo\n");
            Console.printf("[2] Adicionar nova cena vazia\n");
            Console.printf("[3] Salvar cena em arquivo (Try-with-resources)\n");
            Console.printf("[4] Carregar cena de arquivo\n");
            Console.printf("[x] Exit\n");

            // Validação de entrada blindada contra erros
            char option = Console.readOption("**Enter an option", "1234x");

            switch (option) {
                case '1':
                    game.display();
                    break;
                case '2':
                    String novaCena = Console.readString("Digite o nome da nova cena");
                    game.addScene(new Scene(novaCena));
                    Console.printf("Cena '%s' criada com sucesso!\n", novaCena);
                    break;
                case '3':
                    String cenaSalvar = Console.readString("Digite o nome da cena para salvar");
                    try {
                        game.saveSceneToFile(cenaSalvar, cenaSalvar + ".txt");
                    } catch (IOException e) {
                        // A exceção já foi informada no Game.java, apenas mantemos o fluxo do menu
                    }
                    break;
                case '4':
                    String arquivoCarregar = Console.readString("Digite o nome do arquivo para carregar (ex: cena.txt)");
                    try {
                        game.loadSceneFromFile(arquivoCarregar);
                    } catch (IOException e) {
                        // Fluxo contínuo sem quebrar o programa
                    }
                    break;
                case 'x':
                    running = false;
                    Console.printf("\nEncerrando o motor... Ate logo!\n");
                    break;
            }
        }
    }

    /**
     * Inicializa a cena com os objetos padrão.
     */
    private static void setupStandardGame(Scene scene) {
        
        // (1) Cube: Contém Transform (adicionado no construtor) e Mesh
        GameObject cube = new GameObject("Cube", scene, null);
        cube.addComponent(new Mesh(cube, "CubeMesh", "DefaultMaterial"));
        scene.addGameObject(cube);

        // (2) Light: Contém Transform e Light
        GameObject light = new GameObject("Light", scene, null);
        light.addComponent(new Light(light, "Pontual", 255, 255, 255)); // RGB Branco
        scene.addGameObject(light);

        // (3) Main Camera: Contém Transform e Camera
        GameObject camera = new GameObject("Main Camera", scene, null);
        camera.addComponent(new Camera(camera, "Perspectiva", 60.0f, 0.1f, 1000.0f));
        scene.addGameObject(camera);
    }
}