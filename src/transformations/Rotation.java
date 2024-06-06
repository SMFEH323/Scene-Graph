package transformations;

import main.Renderer;

/**
 * A class that represents a rotation transformation. 
 * This class extends the Transform class and provides 
 * additional functionalities specific to rotation.
 * 
 * @author Sayf Elhawary
 */
public class Rotation extends Transform {

    // The angle of rotation
	private float angle;

    /**
     * Constructor to initialize a Rotation object.
     *
     * @param angle The angle of rotation.
     * @param x     The x-coordinate for the rotation.
     * @param y     The y-coordinate for the rotation.
     * @param z     The z-coordinate for the rotation.
     * @param tex   A flag to indicate if texture is enabled.
     */
	public Rotation(float angle, float x, float y, float z, boolean tex) {
		super(x, y, z, tex);  // Calling the parent class constructor
		this.angle = angle;   // Setting the angle of rotation
	}

    /**
     * Get the angle of rotation.
     *
     * @return The angle of rotation.
     */
	public float getAngle() {
		return angle;
	}

    /**
     * Set a new angle of rotation.
     *
     * @param angle The new angle of rotation.
     */
	public void setAngle(float angle) {
		this.angle = angle;
	}

    /**
     * Apply the rotation transformation to a Renderer object.
     * This method delegates the rotation to the Renderer.
     *
     * @param renderer The Renderer object to apply the rotation to.
     */
	public void apply(Renderer renderer) {
		renderer.rotate(this);
	}
}
