/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package app.collections;

import app.components.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ComponentList {
    
    private List<Component> components;

    public ComponentList() {
        this.components = new ArrayList<>();
    }

    /**
     * Adiciona um componente garantindo que não existam duplicatas do mesmo tipo.
     */
    public boolean add(Component component) {
        for (Component c : components) {
            if (c.getClass().equals(component.getClass())) {
                System.out.println("Erro: O GameObject já possui um componente do tipo " + component.getComponentName() + ".");
                return false;
            }
        }
        components.add(component);
        return true;
    }

    public Component get(String className) {
        for (Component c : components) {
            if (c.getComponentName().equalsIgnoreCase(className)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Remove um componente, blindando o Transform contra exclusões.
     */
    public boolean remove(String className) {
        if (className.equalsIgnoreCase("Transform")) {
            System.out.println("Erro: O componente Transform é obrigatório e não pode ser removido.");
            return false;
        }
        
        Component c = get(className);
        if (c != null) {
            components.remove(c);
            return true;
        }
        System.out.println("Erro: Componente " + className + " não encontrado.");
        return false;
    }

    /**
     * Remove todos os componentes, preservando o Transform.
     */
    public void removeAll() {
        Component transform = get("Transform");
        components.clear();
        if (transform != null) {
            components.add(transform);
        }
    }

    /**
     * Método que recebe uma Expressão Lambda para executar uma função em cada componente.
     */
    public void traverse(Consumer<Component> action) {
        for (Component c : components) {
            action.accept(c);
        }
    }
}