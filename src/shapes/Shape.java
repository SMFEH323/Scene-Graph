package shapes;

import main.Renderer;

/**
 * @author Sayf Elhawary and Mohammad Yassin
 */
public abstract class Shape {

	protected String status;

	/**
	 * Constructs a Shape with a specified status.
	 * 
	 * @param status
	 *          The initial status of the shape (e.g., "SOLID", "WIRE").
	 */
	public Shape ( String status ) {
		this.status = status;
	}

	/**
	 * Gets the current status of the shape.
	 * 
	 * @return The current status of the shape.
	 */
	public String getStatus () {
		return status;
	}

	/**
	 * Sets a new status for the shape.
	 * 
	 * @param status
	 *          The new status to be assigned to the shape.
	 */
	public void setStatus ( String status ) {
		this.status = status;
	}

	/**
	 * An abstract method that will be implemented by subclasses to define how the
	 * shape is drawn. Each shape will have its own specific drawing logic
	 * according to its geometry.
	 * 
	 * @param renderer
	 *          The renderer responsible for drawing the shape.
	 */
	public abstract void draw ( Renderer renderer );
}
