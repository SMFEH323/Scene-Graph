package nodes;

import main.Renderer;
import shapes.Shape;

/**
 * @author Sayf Elhawary
 */
public class ShapeNode extends PartialScene {

	private Shape shape;

	/**
	 * Constructs a ShapeNode with a given shape.
	 * 
	 * @param shape
	 *          The shape object to be associated with this node.
	 */
	public ShapeNode ( Shape shape ) {
		super();
		this.shape = shape;
	}

	/**
	 * Retrieves the shape associated with this node.
	 * 
	 * @return The current shape object.
	 */
	public Shape getShape () {
		return shape;
	}

	/**
	 * Sets or updates the shape associated with this node.
	 * 
	 * @param shape
	 *          The new shape to be rendered by this node.
	 */
	public void setShape ( Shape shape ) {
		this.shape = shape;
	}

	/**
	 * Renders the shape using the provided renderer. This method is called when
	 * the scene graph is traversed for rendering, and it delegates the rendering
	 * task to the shape's own drawing method.
	 * 
	 * @param renderer
	 *          The renderer used to draw the shape.
	 */
	public void draw ( Renderer renderer ) {
		shape.draw(renderer);
	}
}
