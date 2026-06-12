/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package app.engine;

import app.collections.GameObjectList;
import app.utils.Console;

public class Scene {
    
    private String name;
    private GameObjectList gameObjects; // A raiz da cena

    public Scene(String name) {
        this.name = name;
        this.gameObjects = new GameObjectList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // --- OPERAÇÕES DA CENA ---

    public void addGameObject(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public boolean removeGameObject(String name) {
        return this.gameObjects.remove(name);
    }

    public void removeAllGameObjects() {
        this.gameObjects.removeAll();
    }

    public GameObject getGameObject(String name) {
        return this.gameObjects.get(name);
    }

    // --- EXIBIÇÃO E ATRAVESSAMENTO ---

    /**
     * Exibe a cena imprimindo seu nome e atravessando a raiz para exibir cada GameObject.
     * Utiliza Expressão Lambda para passar a instrução de display.
     */
    public void display() {
        Console.printf("\n========================================\n");
        Console.printf(" SCENE: %s\n", this.name);
        Console.printf("========================================\n");
        
        this.gameObjects.traverse(go -> go.display());
    }
}