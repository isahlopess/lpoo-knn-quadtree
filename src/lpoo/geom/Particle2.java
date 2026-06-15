/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package lpoo.geom;

public class Particle2 extends Point2 {
    
    // Atributos de cor RGB solicitados no roteiro
    public int r;
    public int g;
    public int b;

    public Particle2() {
        super();
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }

    public Particle2(float x, float y, int r, int g, int b) {
        super(x, y);
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Particle2(Particle2 p) {
        super(p.x, p.y);
        this.r = p.r;
        this.g = p.g;
        this.b = p.b;
    }

    /**
     * Sobrescreve o toString para exibir também a cor na hora do teste no Main.
     */
    @Override
    public String toString() {
        return String.format("<%.2f, %.2f> RGB(%d, %d, %d)", x, y, r, g, b);
    }
}