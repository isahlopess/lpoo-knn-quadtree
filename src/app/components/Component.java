/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package app.components;

import app.engine.GameObject;

public abstract class Component {
    
    // Todo componente conhece o objeto de jogo do qual faz parte 
    protected GameObject gameObject;

    public Component(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public GameObject getGameObject() {
        return this.gameObject;
    }

    // Retorna o nome exato da classe do componente
    public String getComponentName() {
        return this.getClass().getSimpleName();
    }

    // Método abstrato que obriga todas as filhas a implementarem a sua própria inspeção
    public abstract void inspect();
}