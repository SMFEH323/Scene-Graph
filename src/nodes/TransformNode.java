package nodes;

import main.Renderer;
import transformations.Transform;

/**
 * @author Sayf Elhawary and Mohammad Yassin
 */
public class TransformNode extends PartialScene {

	private Transform t;

	/**
	 * Constructs a TransformNode with a specified transformation.
	 * 
	 * @param t
	 *          The transformation to be associated with this node.
	 */
	public TransformNode ( Transform t ) {
		super();
		this.t = t;
	}

	/**
	 * Retrieves the transformation associated with this node.
	 * 
	 * @return The current transformation object.
	 */
	public Transform getTransform () {
		return t;
	}

	/**
	 * Sets or updates the transformation associated with this node.
	 * 
	 * @param t
	 *          The new transformation to be applied by this node.
	 */
	public void setTransform ( Transform t ) {
		this.t = t;
	}

	/**
	 * Applies the transformation to the renderer. This method is called when the
	 * scene graph is rendered, and it influences the positioning, orientation,
	 * and scaling of objects that follow in the rendering sequence.
	 * 
	 * @param renderer
	 *          The renderer to which the transformation is applied.
	 */
	public void draw ( Renderer renderer ) {
		t.apply(renderer);
	}
}
