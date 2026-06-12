/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package app.engine;

import app.collections.SceneList;
import app.utils.Console;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Game {
    
    private SceneList scenes;

    public Game() {
        this.scenes = new SceneList();
        // Quando um novo jogo é criado, contém a cena "Main Scene"
        this.scenes.add(new Scene("Main Scene"));
    }

    // --- OPERAÇÕES BÁSICAS DO JOGO ---

    public void addScene(Scene scene) {
        this.scenes.add(scene);
    }

    public boolean removeScene(String name) {
        return this.scenes.remove(name);
    }

    public void removeAllScenes() {
        this.scenes.removeAll();
    }

    public Scene getScene(String name) {
        return this.scenes.get(name);
    }

    public void display() {
        Console.printf("\n=== EXIBINDO O JOGO ===\n");
        // Atravessando a lista de cenas com Lambda
        this.scenes.traverse(scene -> scene.display());
        Console.printf("=======================\n");
    }

    /**
     * Escreve uma cena da lista em um arquivo de texto.
     * Utiliza try-with-resources para garantir o fechamento automático do BufferedWriter.
     */
    public void saveSceneToFile(String sceneName, String filename) throws IOException {
        Scene scene = getScene(sceneName);
        if (scene == null) {
            Console.printf("Erro: Cena '%s' não encontrada para salvar.\n", sceneName);
            return;
        }

        // O bloco try garante que o recurso será fechado (Closable), dispensando o bloco finally
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("SCENE_NAME:" + scene.getName());
            writer.newLine();
            // (A lógica de serialização profunda da árvore entraria aqui)
            writer.write("STATUS:SavedSuccessfully");
            
            Console.printf("Sucesso! Cena '%s' salva no arquivo '%s'.\n", sceneName, filename);
        } catch (IOException e) {
            Console.printf("Falha ao escrever no arquivo: %s\n", e.getMessage());
            throw e; // Repassa a exceção verificada
        }
    }

    /**
     * Lê uma cena de um arquivo de texto e adiciona à lista do Jogo.
     * Utiliza try-with-resources com BufferedReader para leitura segura.
     */
    public void loadSceneFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            
            if (line != null && line.startsWith("SCENE_NAME:")) {
                String sceneName = line.split(":")[1];
                Scene novaCena = new Scene(sceneName);
                this.addScene(novaCena);
                
                Console.printf("Sucesso! Cena '%s' carregada do arquivo '%s'.\n", sceneName, filename);
            } else {
                Console.printf("Erro: Formato de arquivo de cena inválido.\n");
            }
        } catch (IOException e) {
            Console.printf("Falha ao ler o arquivo: %s\n", e.getMessage());
            throw e; // Repassa a exceção verificada
        }
    }
}