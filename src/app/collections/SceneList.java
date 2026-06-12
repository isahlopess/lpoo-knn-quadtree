/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package app.collections;

import app.engine.Scene;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SceneList {
    
    private List<Scene> scenes;

    public SceneList() {
        this.scenes = new ArrayList<>();
    }

    public void add(Scene scene) {
        scenes.add(scene);
    }

    public Scene get(String name) {
        for (Scene s : scenes) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    public boolean remove(String name) {
        Scene s = get(name);
        if (s != null) {
            scenes.remove(s);
            return true;
        }
        return false;
    }

    public void removeAll() {
        scenes.clear();
    }

    /**
     * Método que recebe uma Expressão Lambda para iterar sobre todas as cenas.
     */
    public void traverse(Consumer<Scene> action) {
        for (Scene s : scenes) {
            action.accept(s);
        }
    }
}