/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package app.components;

import app.engine.GameObject;
import app.utils.Console;

public class Mesh extends Component {
    
    private String meshName;
    private String materialName;

    public Mesh(GameObject gameObject, String meshName, String materialName) {
        super(gameObject);
        this.meshName = meshName;
        this.materialName = materialName;
    }

    @Override
    public void inspect() {
        Console.printf("  Componente: %s | GameObject: %s\n", getComponentName(), gameObject.getName());
        Console.printf("    - Malha: %s\n", meshName);
        Console.printf("    - Material: %s\n", materialName);
    }
}