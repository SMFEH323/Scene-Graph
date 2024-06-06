package transformations;

import main.Renderer;
/**
 * AnimatedTranslation extends the Translation class to provide animated transformations.
 * 
 * @author Sayf Elhawary and Mohammad Yassin
 */
public class AnimatedTranslation extends Translation {

    /**
     * Constructor for creating an instance of AnimatedTranslation.
     *
     * @param x  The x-coordinate for the translation.
     * @param y  The y-coordinate for the translation.
     * @param z  The z-coordinate for the translation.
     * @param tex  A boolean indicating whether texture is enabled or not.
     */
    public AnimatedTranslation(float x, float y, float z, boolean tex) {
        // Calls the constructor of the parent class (Translation) to initialize the instance variables.
        super(x, y, z, tex);
    }
    
    /**
     * Applies the animated translation to a given renderer.
     *
     * @param renderer  The renderer to which the animated translation will be applied.
     */
    public void apply(Renderer renderer) {
        // Calls the animateTranslate method of the renderer to perform the animation.
        renderer.animateTranslate(this);
    }
}