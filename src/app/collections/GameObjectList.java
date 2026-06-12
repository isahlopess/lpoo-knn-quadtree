/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package app.collections;

import app.engine.GameObject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GameObjectList {
    
    private List<GameObject> gameObjects;

    public GameObjectList() {
        this.gameObjects = new ArrayList<>();
    }

    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public GameObject get(String name) {
        for (GameObject go : gameObjects) {
            if (go.getName().equalsIgnoreCase(name)) {
                return go;
            }
        }
        return null;
    }

    public boolean remove(String name) {
        GameObject go = get(name);
        if (go != null) {
            gameObjects.remove(go);
            return true;
        }
        return false;
    }

    public void removeAll() {
        gameObjects.clear();
    }

    /**
     * Método que recebe uma Expressão Lambda para executar uma função em cada GameObject.
     */
    public void traverse(Consumer<GameObject> action) {
        for (GameObject go : gameObjects) {
            action.accept(go);
        }
    }
}