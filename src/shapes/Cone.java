package shapes;

import main.Renderer;

/**
 * @author Sayf Elhawary
 */
public class Cone extends Shape {

	private double base;
	private double height;
	private int slices;
	private int stacks;

	/**
	 * Constructs a Cone with specified status, base radius, height, number of
	 * slices, and stacks.
	 * 
	 * @param status
	 *          The initial status of the cone (e.g., "SOLID").
	 * @param base
	 *          The base radius of the cone.
	 * @param height
	 *          The height of the cone.
	 * @param slices
	 *          The number of slices for approximating the cone's curvature.
	 * @param stacks
	 *          The number of stacked sections along the cone's height.
	 */
	public Cone ( String status, double base, double height, int slices,
	              int stacks ) {
		super(status);
		this.base = base;
		this.height = height;
		this.slices = slices;
		this.stacks = stacks;
	}

	/**
	 * Instructs the renderer to draw this cone.
	 * 
	 * @param renderer
	 *          The renderer responsible for drawing the cone.
	 */
	public void draw ( Renderer renderer ) {
		renderer.drawCone(this);
	}

	/**
	 * Returns the base radius of the cone.
	 * 
	 * @return The base radius of the cone.
	 */
	public double getBase () {
		return this.base;
	}

	/**
	 * Sets the base radius of the cone.
	 * 
	 * @param base
	 *          The new base radius of the cone.
	 */
	public void setBase ( double base ) {
		this.base = base;
	}

	/**
	 * Returns the height of the cone.
	 * 
	 * @return The height of the cone.
	 */
	public double getHeight () {
		return this.height;
	}

	/**
	 * Sets the height of the cone.
	 * 
	 * @param height
	 *          The new height of the cone.
	 */
	public void setHeight ( double height ) {
		this.height = height;
	}

	/**
	 * Returns the number of slices of the cone. Slices are used to approximate
	 * the curvature of the cone's base.
	 * 
	 * @return The number of slices.
	 */
	public int getSlices () {
		return this.slices;
	}

	/**
	 * Sets the number of slices of the cone.
	 * 
	 * @param slices
	 *          The new number of slices.
	 */
	public void setSlices ( int slices ) {
		this.slices = slices;
	}

	/**
	 * Returns the number of stacks of the cone. Stacks are the divisions along
	 * the height of the cone.
	 * 
	 * @return The number of stacks.
	 */
	public int getStacks () {
		return this.stacks;
	}

	/**
	 * Sets the number of stacks of the cone.
	 * 
	 * @param stacks
	 *          The new number of stacks.
	 */
	public void setStacks ( int stacks ) {
		this.stacks = stacks;
	}

	public static final Cone UNIT_CONE = new Cone("SOLID",1,1,32,64);
}
