/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package lpoo.geom;

@FunctionalInterface
public interface PointFunc<P> {
    
    /**
     * Devolve true se o ponto deve ser testado, ou false se deve ser ignorado.
     */
    boolean run(P point);
}