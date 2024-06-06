package shapes;

import main.Renderer;

/**
 * @author Sayf Elhawary and Mohammad Yassin
 */
public class Cylinder extends Shape {

	private double radius;
	private double height;
	private int slices;
	private int stacks;

	/**
	 * Constructs a Cylinder shape with specified attributes.
	 * 
	 * @param status
	 *          The status of the cylinder (e.g., "SOLID", "WIRE").
	 * @param radius
	 *          The radius of the cylinder.
	 * @param height
	 *          The height of the cylinder.
	 * @param slices
	 *          The number of subdivisions around the Z axis (similar to
	 *          longitude).
	 * @param stacks
	 *          The number of subdivisions along the Z axis (similar to latitude).
	 */
	public Cylinder ( String status, double radius, double height, int slices,
	                  int stacks ) {
		super(status);
		this.radius = radius;
		this.height = height;
		this.slices = slices;
		this.stacks = stacks;
	}

	/**
	 * Implements the drawing functionality for the Cylinder. Delegates the
	 * drawing process to the Renderer.
	 * 
	 * @param renderer
	 *          The renderer responsible for drawing the cylinder.
	 */
	public void draw ( Renderer renderer ) {
		renderer.drawCylinder(this);
	}

	/**
	 * Gets the radius of the cylinder.
	 * 
	 * @return The radius of the cylinder.
	 */
	public double getRadius () {
		return this.radius;
	}

	/**
	 * Sets the radius of the cylinder.
	 * 
	 * @param radius
	 *          The new radius to set.
	 */
	public void setRadius ( double radius ) {
		this.radius = radius;
	}

	/**
	 * Gets the height of the cylinder.
	 * 
	 * @return The height of the cylinder.
	 */
	public double getHeight () {
		return this.height;
	}

	/**
	 * Sets the height of the cylinder.
	 * 
	 * @param height
	 *          The new height to set.
	 */
	public void setHeight ( double height ) {
		this.height = height;
	}

	/**
	 * Gets the number of slices of the cylinder.
	 * 
	 * @return The number of slices around the cylinder's circumference.
	 */
	public int getSlices () {
		return this.slices;
	}

	/**
	 * Sets the number of slices of the cylinder.
	 * 
	 * @param slices
	 *          The new number of slices to set.
	 */
	public void setSlices ( int slices ) {
		this.slices = slices;
	}

	/**
	 * Gets the number of stacks of the cylinder.
	 * 
	 * @return The number of stacks along the cylinder's height.
	 */
	public int getStacks () {
		return this.stacks;
	}

	/**
	 * Sets the number of stacks of the cylinder.
	 * 
	 * @param stacks
	 *          The new number of stacks to set.
	 */
	public void setStacks ( int stacks ) {
		this.stacks = stacks;
	}

	public static final Cylinder UNIT_CYLINDER = new Cylinder("SOLID",1,1,32,64);
}
