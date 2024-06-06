package lights;

import main.Renderer;

/**
 * @author Sayf Elhawary
 */
public class DirectionalLight extends Light {

	/**
   * Constructs a DirectionalLight with specified lighting properties.
   *
   * @param lightId   An integer identifier for the light.
   * @param ambient   An array of floats representing the ambient RGB components of the light.
   * @param diffuse   An array of floats representing the diffuse RGB components of the light.
   * @param specular  An array of floats representing the specular RGB components of the light.
   * @param position  An array of floats representing the position of the light in 3D space. 
   *                  Note: For directional lights, this typically represents the direction 
   *                  rather than a specific position.
   */
	public DirectionalLight ( int lightId, float[] ambient, float[] diffuse,
	                          float[] specular, float[] position ) {
		super(lightId,ambient,diffuse,specular,position);
	}

	/**
   * Applies this directional light's properties to the scene using the provided renderer.
   * This method should be used within a rendering context to ensure the light's effect 
   * is visible in the rendered scene.
   *
   * @param renderer  The renderer responsible for handling rendering operations.
   */
	public void apply ( Renderer renderer ) {
		renderer.applyDirectionalLight(this);
	}

}
