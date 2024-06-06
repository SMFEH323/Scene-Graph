package shapes;

import main.Renderer;

/**
 * @author Sayf Elhawary
 */
public class Torus extends Shape {

	private double innerRadius;
	private double outerRadius;
	private int slices;
	private int stacks;

	/**
	 * Constructs a Torus with specified parameters.
	 * 
	 * @param status
	 *          The status of the torus (e.g., solid, wireframe).
	 * @param innerRadius
	 *          The inner radius of the torus.
	 * @param outerRadius
	 *          The outer radius of the torus.
	 * @param slices
	 *          The number of subdivisions around the torus' central axis.
	 * @param stacks
	 *          The number of subdivisions around the torus' thickness.
	 */
	public Torus ( String status, double innerRadius, double outerRadius,
	               int slices, int stacks ) {
		super(status);
		this.innerRadius = innerRadius;
		this.outerRadius = outerRadius;
		this.slices = slices;
		this.stacks = stacks;
	}

	/**
	 * Renders the torus using the provided renderer. The rendering details, such
	 * as the shape's position, orientation, and visual style, are handled by the
	 * renderer.
	 * 
	 * @param renderer
	 *          The renderer responsible for drawing the torus.
	 */
	public void draw ( Renderer renderer ) {
		renderer.drawTorus(this);
	}

	/**
	 * Returns the inner radius of the torus.
	 * 
	 * @return The inner radius.
	 */
	public double getInnerRadius () {
		return this.innerRadius;
	}

	/**
	 * Sets the inner radius of the torus.
	 * 
	 * @param innerRadius
	 *          The new inner radius.
	 */
	public void setInnerRadius ( double innerRadius ) {
		this.innerRadius = innerRadius;
	}

	/**
	 * Returns the outer radius of the torus.
	 * 
	 * @return The outer radius.
	 */
	public double getOuterRadius () {
		return this.outerRadius;
	}

	/**
	 * Sets the outer radius of the torus.
	 * 
	 * @param outerRadius
	 *          The new outer radius.
	 */
	public void setOuterRadius ( double outerRadius ) {
		this.outerRadius = outerRadius;
	}

	/**
	 * Returns the number of slices of the torus.
	 * 
	 * @return The number of slices.
	 */
	public int getSlices () {
		return this.slices;
	}

	/**
	 * Sets the number of slices of the torus.
	 * 
	 * @param slices
	 *          The new number of slices.
	 */
	public void setSlices ( int slices ) {
		this.slices = slices;
	}

	/**
	 * Returns the number of stacks of the torus.
	 * 
	 * @return The number of stacks.
	 */
	public int getStacks () {
		return this.stacks;
	}

	/**
	 * Sets the number of stacks of the torus.
	 * 
	 * @param stacks
	 *          The new number of stacks.
	 */
	public void setStacks ( int stacks ) {
		this.stacks = stacks;
	}

	public static final Torus UNIT_TORUS = new Torus("SOLID",.5,1,32,64);

}
