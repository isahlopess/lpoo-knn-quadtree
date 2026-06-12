/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package app.components;

import app.engine.GameObject;
import app.utils.Console;

public class Transform extends Component {
    
    // Usando arrays de 3 posições para representar os vetores 3D (X, Y, Z)
    private float[] position = {0.0f, 0.0f, 0.0f};
    private float[] rotation = {0.0f, 0.0f, 0.0f};
    private float scale = 1.0f;

    public Transform(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void inspect() {
        Console.printf("  Componente: %s | GameObject: %s\n", getComponentName(), gameObject.getName());
        Console.printf("    - Posicao: [%.2f, %.2f, %.2f]\n", position[0], position[1], position[2]);
        Console.printf("    - Rotacao: [%.2f, %.2f, %.2f]\n", rotation[0], rotation[1], rotation[2]);
        Console.printf("    - Escala: %.2f\n", scale);
    }
}