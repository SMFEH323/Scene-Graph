package nodes;

import java.util.ArrayList;
import java.util.List;

import main.Renderer;

/**
 * @author Sayf Elhawary
 */
public class PartialScene {
	private List<PartialScene> children;

	/**
	 * Constructs a new PartialScene node with an empty list of children.
	 */
	public PartialScene () {
		children = new ArrayList<>();
	}

	/**
	 * Adds a child node to this PartialScene. This method allows for building a
	 * hierarchical structure in the scene graph by adding child nodes to the
	 * current node.
	 * 
	 * @param child
	 *          The PartialScene node to be added as a child.
	 */
	public void addChild ( PartialScene child ) {
		children.add(child);
	}

	/**
	 * Removes a child node from this PartialScene. If the specified node is not a
	 * child, this method has no effect.
	 * 
	 * @param child
	 *          The PartialScene node to be removed.
	 */
	public void removeChild ( PartialScene child ) {
		children.remove(child);
	}

	/**
	 * Renders this PartialScene and all its children using the provided renderer.
	 * This method recursively traverses the scene graph, rendering each node. It
	 * also ensures that transformations applied to parent nodes are saved and
	 * restored appropriately, maintaining the correct rendering context.
	 * 
	 * @param renderer
	 *          The renderer used to render this PartialScene and its children.
	 */
	public void draw ( Renderer renderer ) {
		renderer.saveTransform();
		for ( PartialScene child : children ) {
			child.draw(renderer);
		}
		renderer.restoreTransform();
	}
}
