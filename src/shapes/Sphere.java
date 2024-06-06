package shapes;

import main.Renderer;

/**
 * @author Sayf Elhawary and Mohammad Yassin
 */
public class Sphere extends Shape {
	private double radius;
	private int slices;
	private int stacks;

	/**
	 * Constructs a new Sphere object with specified status, radius, slices, and
	 * stacks.
	 * 
	 * @param status
	 *          The status of the sphere (e.g., "SOLID").
	 * @param radius
	 *          The radius of the sphere.
	 * @param slices
	 *          The number of slices (longitude divisions) of the sphere.
	 * @param stacks
	 *          The number of stacks (latitude divisions) of the sphere.
	 */
	public Sphere ( String status, double radius, int slices, int stacks ) {
		super(status);
		this.radius = radius;
		this.slices = slices;
		this.stacks = stacks;
	}

	/**
	 * Draws the sphere using the provided renderer.
	 * 
	 * @param renderer
	 *          The renderer used to draw the sphere.
	 */
	public void draw ( Renderer renderer ) {
		renderer.drawSphere(this);
	}

	/**
	 * Returns the radius of the sphere.
	 * 
	 * @return The current radius of the sphere.
	 */
	public double getRadius () {
		return this.radius;
	}

	/**
	 * Sets the radius of the sphere.
	 * 
	 * @param radius
	 *          The new radius for the sphere.
	 */
	public void setRadius ( double radius ) {
		this.radius = radius;
	}

	/**
	 * Returns the number of slices of the sphere.
	 * 
	 * @return The current number of slices (longitude divisions).
	 */
	public int getSlices () {
		return this.slices;
	}

	/**
	 * Sets the number of slices (longitude divisions) of the sphere.
	 * 
	 * @param slices
	 *          The new number of slices for the sphere.
	 */
	public void setSlices ( int slices ) {
		this.slices = slices;
	}

	/**
	 * Returns the number of stacks of the sphere.
	 * 
	 * @return The current number of stacks (latitude divisions).
	 */
	public int getStacks () {
		return this.stacks;
	}

	/**
	 * Sets the number of stacks (latitude divisions) of the sphere.
	 * 
	 * @param stacks
	 *          The new number of stacks for the sphere.
	 */
	public void setStacks ( int stacks ) {
		this.stacks = stacks;
	}

	public static final Sphere UNIT_SPHERE = new Sphere("SOLID",1,32,64);

}