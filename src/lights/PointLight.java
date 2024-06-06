package lights;

import main.Renderer;

/**
 * @author Sayf Elhawary and Mohammad Yassin
 */
public class PointLight extends Light {

	/**
	 * Constructs a PointLight object with specified lighting characteristics and
	 * position.
	 *
	 * @param lightId
	 *          An integer representing the unique identifier for the light.
	 * @param ambient
	 *          An array of floats representing the ambient RGBA intensity of the
	 *          light.
	 * @param diffuse
	 *          An array of floats representing the diffuse RGBA intensity of the
	 *          light.
	 * @param specular
	 *          An array of floats representing the specular RGBA intensity of the
	 *          light.
	 * @param position
	 *          An array of floats representing the position of the light in 3D
	 *          space.
	 */
	public PointLight ( int lightId, float[] ambient, float[] diffuse,
	                    float[] specular, float[] position ) {
		super(lightId,ambient,diffuse,specular,position);
	}

	/**
	 * Applies this point light's settings to the given renderer. This typically
	 * involves setting the light's position, and its ambient, diffuse, and
	 * specular components in the renderer's context.
	 *
	 * @param renderer
	 *          The renderer to which the light settings will be applied.
	 */
	public void apply ( Renderer renderer ) {
		renderer.applyPointLight(this);
	}

}
