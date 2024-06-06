package transformations;

import main.Renderer;

/**
 * AnimatedScale is a subclass of Scale, designed to handle animated scaling transformations.
 * Authors: Sayf Elhawary and Mohammad Yassin
 */
public class AnimatedScale extends Scale {

    /**
     * Constructor for AnimatedScale class.
     * @param x The scale factor along the x-axis.
     * @param y The scale factor along the y-axis.
     * @param z The scale factor along the z-axis.
     * @param tex A boolean flag to indicate whether to use textures or not.
     */
    public AnimatedScale(float x, float y, float z, boolean tex) {
        // Call the constructor of the superclass, Scale.
        super(x, y, z, tex);
    }

    /**
     * Apply the animated scaling transformation using a Renderer object.
     * @param renderer The Renderer object responsible for performing the animation.
     */
    public void apply(Renderer renderer) {
        // Call the animateScale method from the Renderer object.
        renderer.animateScale(this);
    }
}