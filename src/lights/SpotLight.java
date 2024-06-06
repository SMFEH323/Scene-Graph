package lights;

import main.Renderer;

/**
 * @author Sayf Elhawary
 */
public class SpotLight extends Light {

	private float[] direction;
	private float cutoff;
	private float exponent;

	/**
	 * Constructs a new Spotlight with specified properties.
	 *
	 * @param lightId
	 *          The unique identifier for this light.
	 * @param ambient
	 *          The ambient RGBA reflectance of the light.
	 * @param diffuse
	 *          The diffuse RGBA reflectance of the light.
	 * @param specular
	 *          The specular RGBA reflectance of the light.
	 * @param position
	 *          The position of the light in 3D space.
	 * @param direction
	 *          The direction vector of the light.
	 * @param cutoff
	 *          The cutoff angle of the spotlight in degrees.
	 * @param exponent
	 *          The falloff exponent of the spotlight's intensity.
	 */
	public SpotLight ( int lightId, float[] ambient, float[] diffuse,
	                   float[] specular, float[] position, float[] direction,
	                   float cutoff, float exponent ) {
		super(lightId,ambient,diffuse,specular,position);
		this.direction = direction;
		this.cutoff = cutoff;
		this.exponent = exponent;
	}

	/**
	 * Returns the direction vector of the spotlight.
	 *
	 * @return A float array representing the direction vector.
	 */
	public float[] getDirection () {
		return direction;
	}

	/**
	 * Returns the cutoff angle of the spotlight.
	 *
	 * @return The cutoff angle in degrees.
	 */
	public float getCutoff () {
		return cutoff;
	}

	/**
	 * Returns the exponent of the spotlight's intensity falloff.
	 *
	 * @return The intensity falloff exponent.
	 */
	public float getExponent () {
		return exponent;
	}

	/**
	 * Sets the direction of the spotlight.
	 *
	 * @param x
	 *          The X component of the direction vector.
	 * @param y
	 *          The Y component of the direction vector.
	 * @param z
	 *          The Z component of the direction vector.
	 */
	public void setDirection ( float x, float y, float z ) {
		direction = new float[] { x, y, z };
	}

	/**
	 * Sets the cutoff angle of the spotlight.
	 *
	 * @param cutoff
	 *          The new cutoff angle in degrees.
	 */
	public void setCutoff ( float cutoff ) {
		this.cutoff = cutoff;
	}

	/**
	 * Sets the exponent of the spotlight's intensity falloff.
	 *
	 * @param exponent
	 *          The new intensity falloff exponent.
	 */
	public void setDirection ( float exponent ) {
		this.exponent = exponent;
	}

	/**
	 * Applies the properties of this spotlight to the renderer.
	 * 
	 * @param renderer
	 *          The renderer to which the light properties are to be applied.
	 */
	public void apply ( Renderer renderer ) {
		renderer.applySpotLight(this);
	}

}
