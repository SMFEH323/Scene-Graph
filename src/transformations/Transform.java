package transformations;

import main.Renderer;

/**
 * @author Sayf Elhawary
 */
public abstract class Transform {

	private float x;
	private float y;
	private float z;
	private boolean tex;

	/**
	 * Constructs a Transform with specified coordinates and a boolean flag.
	 * 
	 * @param x
	 *          The x-coordinate of the transformation.
	 * @param y
	 *          The y-coordinate of the transformation.
	 * @param z
	 *          The z-coordinate of the transformation.
	 * @param tex
	 *          The boolean flag related to the transformation (its specific
	 *          meaning can vary).
	 */
	public Transform ( float x, float y, float z, boolean tex ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.tex = tex;
	}

	/**
	 * Sets the X coordinate for the transformation.
	 *
	 * @param x
	 *          New X coordinate
	 */
	public void setX ( float x ) {
		this.x = x;
	}

	/**
	 * Sets the Y coordinate for the transformation.
	 *
	 * @param y
	 *          New Y coordinate
	 */
	public void setY ( float y ) {
		this.y = y;
	}

	/**
	 * Sets the Z coordinate for the transformation.
	 *
	 * @param z
	 *          New Z coordinate
	 */
	public void setZ ( float z ) {
		this.z = z;
	}

	/**
	 * Sets the texture flag for the transformation.
	 *
	 * @param tex
	 *          New texture flag
	 */
	public void setTex ( boolean tex ) {
		this.tex = tex;
	}

	/**
	 * Gets the X coordinate for the transformation.
	 *
	 * @return Current X coordinate
	 */
	public float getX () {
		return x;
	}

	/**
	 * Gets the Y coordinate for the transformation.
	 *
	 * @return Current Y coordinate
	 */
	public float getY () {
		return y;
	}

	/**
	 * Gets the Z coordinate for the transformation.
	 *
	 * @return Current Z coordinate
	 */
	public float getZ () {
		return z;
	}

	/**
	 * Gets the current texture flag.
	 *
	 * @return Current texture flag
	 */
	public boolean getTex () {
		return tex;
	}

	/**
	 * Abstract method that applies the transformation to a given Renderer.
	 * Subclasses must provide an implementation for this method.
	 *
	 * @param renderer
	 *          Renderer object to which the transformation will be applied
	 */
	public abstract void apply ( Renderer renderer );

}
