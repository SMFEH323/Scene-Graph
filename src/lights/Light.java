package lights;

import main.Renderer;

/**
 * @author Sayf Elhawary and Mohammad Yassin
 */
public abstract class Light {

	private int lightId;
	private float[] ambient;
	private float[] diffuse;
	private float[] specular;
	private float[] position;

	/**
	 * Constructs a Light object with specified properties.
	 *
	 * @param lightId
	 *          the identifier of the light
	 * @param ambient
	 *          the ambient light color components
	 * @param diffuse
	 *          the diffuse light color components
	 * @param specular
	 *          the specular light color components
	 * @param position
	 *          the position of the light in the scene
	 */
	public Light ( int lightId, float[] ambient, float[] diffuse,
	               float[] specular, float[] position ) {
		this.lightId = lightId;
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		this.position = position;
	}

	/**
	 * Sets the ambient light color.
	 *
	 * @param r
	 *          the red component
	 * @param g
	 *          the green component
	 * @param b
	 *          the blue component
	 * @param a
	 *          the alpha component
	 */
	public void setAmbient ( float r, float g, float b, float a ) {
		ambient = new float[] { r, g, b, a };
	}

	/**
	 * Sets the diffuse light color.
	 *
	 * @param r
	 *          the red component
	 * @param g
	 *          the green component
	 * @param b
	 *          the blue component
	 * @param a
	 *          the alpha component
	 */
	public void setDiffuse ( float r, float g, float b, float a ) {
		diffuse = new float[] { r, g, b, a };
	}

	/**
	 * Sets the specular light color.
	 *
	 * @param r
	 *          the red component
	 * @param g
	 *          the green component
	 * @param b
	 *          the blue component
	 * @param a
	 *          the alpha component
	 */
	public void setSpecular ( float r, float g, float b, float a ) {
		specular = new float[] { r, g, b, a };
	}

	/**
	 * Returns the ambient light color.
	 *
	 * @return the ambient light color components
	 */
	public float[] getAmbient () {
		return ambient;
	}

	/**
	 * Returns the diffuse light color.
	 *
	 * @return the diffuse light color components
	 */
	public float[] getDiffuse () {
		return diffuse;
	}

	/**
	 * Returns the specular light color.
	 *
	 * @return the specular light color components
	 */
	public float[] getSpecular () {
		return specular;
	}

	/**
	 * Returns the position of the light.
	 *
	 * @return the position of the light in the scene
	 */
	public float[] getPosition () {
		return position;
	}

	/**
	 * Sets the position of the light.
	 *
	 * @param x
	 *          the x-coordinate of the light position
	 * @param y
	 *          the y-coordinate of the light position
	 * @param z
	 *          the z-coordinate of the light position
	 */
	public void setPosition ( float x, float y, float z ) {
		position = new float[] { x, y, z };
	}

	/**
	 * Returns the light identifier.
	 *
	 * @return the identifier of the light
	 */
	public int getLightId () {
		return lightId;
	}

	/**
	 * Sets the light identifier.
	 *
	 * @param lightId
	 *          the new identifier of the light
	 */
	public void setLightId ( int lightId ) {
		this.lightId = lightId;
	}

	/**
	 * Abstract method to apply the light properties using the given renderer.
	 * This needs to be implemented by subclasses to define how the light affects
	 * the rendered scene.
	 *
	 * @param renderer
	 *          the renderer to which the light properties are applied
	 */
	public abstract void apply ( Renderer renderer );
}
