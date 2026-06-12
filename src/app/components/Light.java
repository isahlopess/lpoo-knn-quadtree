/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package app.components;

import app.engine.GameObject;
import app.utils.Console;

public class Light extends Component {
    
    private String type; // "Pontual" ou "Direcional"
    private int[] colorRGB = new int[3]; // R, G, B

    public Light(GameObject gameObject, String type, int r, int g, int b) {
        super(gameObject);
        this.type = type;
        this.colorRGB[0] = r;
        this.colorRGB[1] = g;
        this.colorRGB[2] = b;
    }

    @Override
    public void inspect() {
        Console.printf("  Componente: %s | GameObject: %s\n", getComponentName(), gameObject.getName());
        Console.printf("    - Tipo: %s\n", type);
        Console.printf("    - Cor (RGB): [%d, %d, %d]\n", colorRGB[0], colorRGB[1], colorRGB[2]);
    }
}