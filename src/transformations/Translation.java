package transformations;

import main.Renderer;

/**
 * @author Sayf Elhawary and Mohammad Yassin
 * 
 * This class handles the translation transformation by extending the Transform class.
 * Translation moves an object along the X, Y, and Z axes.
 */
public class Translation extends Transform {

	/**
	 * Constructor for the Translation class.
	 * 
	 * @param x The translation along the X-axis.
	 * @param y The translation along the Y-axis.
	 * @param z The translation along the Z-axis.
	 * @param tex A boolean value indicating whether textures are to be used.
	 */
	public Translation (float x, float y, float z, boolean tex) {
		// Call the constructor of the parent (Transform) class
		super(x, y, z, tex);
	}

	/**
	 * Apply the translation to the renderer.
	 * 
	 * @param renderer The renderer object that needs to be translated.
	 */
	public void apply(Renderer renderer) {
		// Use the renderer's translate method to apply the translation
		renderer.translate(this);
	}
}