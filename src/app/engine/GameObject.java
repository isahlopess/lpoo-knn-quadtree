/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package app.engine;

import app.collections.ComponentList;
import app.collections.GameObjectList;
import app.components.Component;
import app.components.Transform;
import app.utils.Console;

public class GameObject {
    
    private String name;
    private Scene scene;            
    private GameObject parent;      
    
    private ComponentList components;
    private GameObjectList children;

    /**
     * Construtor da classe.
     * Quando um GameObject é criado, ele já contém um Transform.
     */
    public GameObject(String name, Scene scene, GameObject parent) {
        this.name = name;
        this.scene = scene;
        this.parent = parent;
        
        this.components = new ComponentList();
        this.children = new GameObjectList();

        // Adiciona o Transform obrigatório na criação
        this.components.add(new Transform(this));
    }

    // --- GETTERS E SETTERS BÁSICOS ---
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scene getScene() {
        return scene;
    }

    public GameObject getParent() {
        return parent;
    }

    // --- OPERAÇÕES COM COMPONENTES ---

    public boolean addComponent(Component component) {
        return this.components.add(component);
    }

    public boolean removeComponent(String className) {
        return this.components.remove(className);
    }

    public Component getComponent(String className) {
        return this.components.get(className);
    }

    public void removeAllComponents() {
        this.components.removeAll();
    }

    // --- OPERAÇÕES COM OBJETOS FILHOS ---

    /**
     * Adiciona um filho garantindo que o filho reconheça este objeto como pai
     * e pertença à mesma cena.
     */
    public void addChild(GameObject child) {
        child.parent = this;
        child.scene = this.scene;
        this.children.add(child);
    }

    public boolean removeChild(String name) {
        return this.children.remove(name);
    }

    public GameObject getChild(String name) {
        return this.children.get(name);
    }

    public void removeAllChildren() {
        this.children.removeAll();
    }

    // --- EXIBIÇÃO (INSPEÇÃO) ---

    /**
     * Exibe o GameObject imprimindo seu nome, nome do pai, inspecionando todos os 
     * seus componentes e, recursivamente, exibindo todos os seus filhos.
     */
    public void display() {
        String parentName = (parent != null) ? parent.getName() : "Nenhum (Raiz)";
        Console.printf("\n[GameObject: %s | Pai: %s]\n", this.name, parentName);
        
        // Atravessa a lista de componentes usando Expressão Lambda para inspecionar cada um
        Console.printf("  --- Componentes ---\n");
        this.components.traverse(c -> c.inspect());

        // Atravessa a lista de filhos usando Expressão Lambda para exibi-los recursivamente
        Console.printf("  --- Filhos de %s ---\n", this.name);
        this.children.traverse(child -> child.display());
    }
}