package nodes;

import main.Renderer;

/**
 * @author Sayf Elhawary
 */
public class Scene {

	private PartialScene rootNode;

	private float[] bgColors;

	/**
	 * Constructs a Scene with a specified background color.
	 * 
	 * @param r
	 *          The red component of the background color.
	 * @param g
	 *          The green component of the background color.
	 * @param b
	 *          The blue component of the background color.
	 * @param a
	 *          The alpha (transparency) component of the background color.
	 */
	public Scene ( float r, float g, float b, float a ) {
		rootNode = new PartialScene();
		bgColors = new float[] { r, g, b, a };
	}

	/**
	 * Adds a child node to the root node of the scene.
	 * 
	 * @param child
	 *          The PartialScene node to be added to the scene.
	 */
	public void add ( PartialScene child ) {
		rootNode.addChild(child);
	}

	/**
	 * Removes a child node from the root node of the scene.
	 * 
	 * @param child
	 *          The PartialScene node to be removed from the scene.
	 */
	public void remove ( PartialScene child ) {
		rootNode.removeChild(child);
	}

	/**
	 * Retrieves the root node of the scene graph.
	 * 
	 * @return The root node of the scene.
	 */
	public PartialScene getRootNode () {
		return rootNode;
	}

	/**
	 * Gets the background color of the scene.
	 * 
	 * @return An array of floats representing the RGBA values of the background
	 *         color.
	 */
	public float[] getBGColors () {
		return bgColors;
	}

	/**
	 * Renders the entire scene using the provided renderer. This method starts
	 * the rendering process at the root node of the scene graph, effectively
	 * drawing the entire scene.
	 * 
	 * @param renderer
	 *          The renderer used to render the scene.
	 */
	public void draw ( Renderer renderer ) {
		rootNode.draw(renderer);
	}
}
