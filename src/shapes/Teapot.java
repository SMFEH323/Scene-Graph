package shapes;

import main.Renderer;

/**
 * @author Sayf Elhawary and Mohammad Yassin
 */
public class Teapot extends Shape {

	private float sideLength;

	/**
	 * Constructs a new Teapot object with a specified status and side length.
	 * 
	 * @param status
	 *          The status of the teapot (e.g., "SOLID").
	 * @param sideLength
	 *          The length of the sides of the teapot, determining its size.
	 */
	public Teapot ( String status, float sideLength ) {
		super(status);
		this.sideLength = sideLength;
	}

	/**
	 * Draws the teapot using the provided renderer.
	 * 
	 * @param renderer
	 *          The renderer used to draw the teapot.
	 */
	public void draw ( Renderer renderer ) {
		renderer.drawTeapot(this);
	}

	/**
	 * Returns the side length of the teapot.
	 * 
	 * @return The side length of the teapot.
	 */
	public float getSideLength () {
		return this.sideLength;
	}

	/**
	 * Sets the side length of the teapot, thus changing its size.
	 * 
	 * @param sideLength
	 *          The new side length for the teapot.
	 */
	public void setSideLength ( float sideLength ) {
		this.sideLength = sideLength;
	}

	public static final Teapot UNIT_TEAPUT = new Teapot("SOLID",1);

}
