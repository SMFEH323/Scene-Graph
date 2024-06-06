package transformations;

import main.Renderer;

/**
 * Scale class, which extends the Transform class.
 * It is responsible for scaling objects in a 3D space.
 * 
 * @author Sayf Elhawary and Mohammad Yassin
 */
public class Scale extends Transform {

    /**
     * Constructor for the Scale class.
     * Initializes the scale factors along the x, y, and z axes,
     * and also a flag that indicates whether to scale textures.
     *
     * @param x  Scale factor along the x-axis
     * @param y  Scale factor along the y-axis
     * @param z  Scale factor along the z-axis
     * @param tex  Flag to indicate whether to scale textures
     */
    public Scale(float x, float y, float z, boolean tex) {
        // Calling the superclass constructor to initialize fields
        super(x, y, z, tex);
    }

    /**
     * Apply the scaling transformation using a Renderer instance.
     *
     * @param renderer  The Renderer instance to which the scaling should be applied
     */
    public void apply(Renderer renderer) {
        // Calling the scale method of the Renderer instance,
        // passing this Scale object to it
        renderer.scale(this);
    }

}