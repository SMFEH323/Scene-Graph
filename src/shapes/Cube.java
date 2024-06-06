package shapes;

import main.Renderer;

/**
 * @author Sayf Elhawary
 */
public class Cube extends Shape {

	private float sideLength;

	/**
	 * Constructs a Cube with a specified status and side length.
	 * 
	 * @param status
	 *          The initial status of the cube (e.g., "SOLID").
	 * @param sideLength
	 *          The length of each side of the cube.
	 */
	public Cube ( String status, float sideLength ) {
		super(status);
		this.sideLength = sideLength;
	}

	/**
	 * Instructs the renderer to draw this cube.
	 * 
	 * @param renderer
	 *          The renderer responsible for drawing the cube.
	 */
	public void draw ( Renderer renderer ) {
		renderer.drawCube(this);
	}

	/**
	 * Gets the length of the sides of the cube.
	 * 
	 * @return The side length of the cube.
	 */
	public float getSideLength () {
		return this.sideLength;
	}

	/**
	 * Sets the length of the sides of the cube.
	 * 
	 * @param sideLength
	 *          The new side length to set.
	 */
	public void setSideLength ( float sideLength ) {
		this.sideLength = sideLength;
	}

	public static final Cube UNIT_CUBE = new Cube("SOLID",1);
}
