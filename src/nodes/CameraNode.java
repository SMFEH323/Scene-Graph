package nodes;

import main.Camera;
import main.Renderer;

/**
 * @author Sayf Elhawary and Mohammad Yassin
 */
public class CameraNode extends PartialScene {

	private Camera camera;

	/**
	 * Constructs a CameraNode with the specified camera.
	 * 
	 * @param camera
	 *          The camera associated with this node.
	 */
	public CameraNode ( Camera camera ) {
		super();
		this.camera = camera;
	}

	/**
	 * Gets the camera associated with this node.
	 * 
	 * @return The current camera object.
	 */
	public Camera getCamera () {
		return camera;
	}

	/**
	 * Sets or updates the camera associated with this node.
	 * 
	 * @param camera
	 *          The new camera to be associated with this node.
	 */
	public void setCamera ( Camera camera ) {
		this.camera = camera;
	}

	/**
	 * Applies the camera's view to the renderer. This method is called when the
	 * scene graph is rendered, and it configures the renderer according to the
	 * camera's settings.
	 * 
	 * @param renderer
	 *          The renderer to which the camera's view settings are applied.
	 */
	public void draw ( Renderer renderer ) {
		camera.apply(renderer);
	}
}
