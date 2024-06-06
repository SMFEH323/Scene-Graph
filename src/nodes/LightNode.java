package nodes;

import lights.Light;
import main.Renderer;

/**
 * @author Sayf Elhawary and Mohammad Yassin
 */
public class LightNode extends PartialScene {

	private Light light;

	/**
	 * Constructs a LightNode with the specified light.
	 * 
	 * @param light
	 *          The light source associated with this node.
	 */
	public LightNode ( Light light ) {
		super();
		this.light = light;
	}

	/**
	 * Retrieves the light source associated with this node.
	 * 
	 * @return The current light object.
	 */
	public Light getLight () {
		return light;
	}

	/**
	 * Sets or updates the light source associated with this node.
	 * 
	 * @param light
	 *          The new light source to be associated with this node.
	 */
	public void setLight ( Light light ) {
		this.light = light;
	}

	/**
	 * Applies the light's settings to the renderer. This method is called when
	 * the scene graph is rendered, and it configures how the light source affects
	 * the appearance of objects in the scene.
	 * 
	 * @param renderer
	 *          The renderer to which the light's settings are applied.
	 */
	public void draw ( Renderer renderer ) {
		light.apply(renderer);
	}
}
