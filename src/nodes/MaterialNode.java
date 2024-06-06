package nodes;

import main.Material;
import main.Renderer;

/**
 * @author Sayf Elhawary
 */
public class MaterialNode extends PartialScene {

	private Material material;

	/**
	 * Constructs a MaterialNode with the specified material.
	 * 
	 * @param material
	 *          The material to be associated with this node.
	 */
	public MaterialNode ( Material material ) {
		super();
		this.material = material;
	}

	/**
	 * Retrieves the material associated with this node.
	 * 
	 * @return The current material object.
	 */
	public Material getMaterial () {
		return material;
	}

	/**
	 * Sets or updates the material associated with this node.
	 * 
	 * @param material
	 *          The new material to be associated with this node.
	 */
	public void setMaterial ( Material material ) {
		this.material = material;
	}

	/**
	 * Applies the material's properties to the renderer. This method is called
	 * when the scene graph is rendered, and it adjusts the rendering properties
	 * according to the material's characteristics.
	 * 
	 * @param renderer
	 *          The renderer to which the material's properties are applied.
	 */
	public void draw ( Renderer renderer ) {
		material.apply(renderer);
	}
}
