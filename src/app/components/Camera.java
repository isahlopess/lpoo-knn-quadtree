/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package app.components;

import app.engine.GameObject;
import app.utils.Console;

public class Camera extends Component {
    
    private String projectionType; // "Perspectiva" ou "Paralela"
    private float fieldOfView;
    private float nearClipPlane;
    private float farClipPlane;

    public Camera(GameObject gameObject, String projectionType, float fov, float near, float far) {
        super(gameObject);
        this.projectionType = projectionType;
        this.fieldOfView = fov;
        this.nearClipPlane = near;
        this.farClipPlane = far;
    }

    @Override
    public void inspect() {
        Console.printf("  Componente: %s | GameObject: %s\n", getComponentName(), gameObject.getName());
        Console.printf("    - Projecao: %s\n", projectionType);
        Console.printf("    - Angulo de Vista (FOV): %.2f\n", fieldOfView);
        Console.printf("    - Planos de Recorte: Near=%.2f, Far=%.2f\n", nearClipPlane, farClipPlane);
    }
}