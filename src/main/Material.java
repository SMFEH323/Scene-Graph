package main;

/**
 * @author Sayf Elhawary and Mohammad Yassin
 */
public class Material {

	private float[] ambient;
	private float[] diffuse;
	private float[] specular;
	private float[] emissive;
	private float shininess;

	/**
	 * Constructs a Material instance with specified properties.
	 * 
	 * @param ambient
	 *          Ambient light property as an array of RGBA values.
	 * @param diffuse
	 *          Diffuse light property as an array of RGBA values.
	 * @param specular
	 *          Specular light property as an array of RGBA values.
	 * @param emissive
	 *          Emissive light property as an array of RGBA values.
	 * @param shininess
	 *          Shininess property.
	 */
	public Material ( float[] ambient, float[] diffuse, float[] specular,
	                  float[] emissive, float shininess ) {

		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		this.emissive = emissive;
		this.shininess = shininess;
	}

	/**
	 * Sets the ambient property of the material.
	 * 
	 * @param r
	 *          Red component (0-1 range).
	 * @param g
	 *          Green component (0-1 range).
	 * @param b
	 *          Blue component (0-1 range).
	 * @param a
	 *          Alpha component (0-1 range).
	 */
	public void setAmbient ( float r, float g, float b, float a ) {
		ambient = new float[] { r, g, b, a };
	}

	/**
	 * Sets the diffuse property of the material.
	 * 
	 * @param r
	 *          Red component (0-1 range).
	 * @param g
	 *          Green component (0-1 range).
	 * @param b
	 *          Blue component (0-1 range).
	 * @param a
	 *          Alpha component (0-1 range).
	 */
	public void setDiffuse ( float r, float g, float b, float a ) {
		diffuse = new float[] { r, g, b, a };
	}

	/**
	 * Sets the specular property of the material.
	 * 
	 * @param r
	 *          Red component (0-1 range).
	 * @param g
	 *          Green component (0-1 range).
	 * @param b
	 *          Blue component (0-1 range).
	 * @param a
	 *          Alpha component (0-1 range).
	 */
	public void setSpecular ( float r, float g, float b, float a ) {
		specular = new float[] { r, g, b, a };
	}

	/**
	 * Sets the emissive property of the material.
	 * 
	 * @param r
	 *          Red component (0-1 range).
	 * @param g
	 *          Green component (0-1 range).
	 * @param b
	 *          Blue component (0-1 range).
	 * @param a
	 *          Alpha component (0-1 range).
	 */
	public void setEmissive ( float r, float g, float b, float a ) {
		emissive = new float[] { r, g, b, a };
	}

	/**
	 * Sets the shininess property of the material
	 * 
	 * @param shininess
	 */
	public void setShininess ( float shininess ) {
		this.shininess = shininess;
	}

	/**
	 * Returns the ambient property of the material.
	 * 
	 * @return Array representing the RGBA values of the ambient property.
	 */
	public float[] getAmbient () {
		return ambient;
	}

	/**
	 * Returns the diffuse property of the material.
	 * 
	 * @return Array representing the RGBA values of the ambient property.
	 */
	public float[] getDiffuse () {
		return diffuse;
	}

	/**
	 * Returns the specular property of the material.
	 * 
	 * @return Array representing the RGBA values of the ambient property.
	 */
	public float[] getSpecular () {
		return specular;
	}

	/**
	 * Returns the emissive property of the material.
	 * 
	 * @return Array representing the RGBA values of the ambient property.
	 */
	public float[] getEmissive () {
		return emissive;
	}

	/**
	 * Returns the shininess property of the material.
	 * 
	 * @return Array representing the RGBA values of the ambient property.
	 */
	public float getShininess () {
		return shininess;
	}

	/**
	 * Applies this material's properties to a given renderer.
	 * 
	 * @param renderer
	 *          The renderer on which this material is to be applied.
	 */
	public void apply ( Renderer renderer ) {
		renderer.applyMaterial(this);
	}

	public static final Material CYAN_RUBBER =
	    new Material(new float[] { 0, 0.05f, 0.05f, 1 },
	                 new float[] { 0.4f, 0.5f, 0.5f, 1 },
	                 new float[] { 0.04f, 0.7f, 0.7f, 1 },
	                 new float[] { 0, 0, 0, 1 },(float) (0.078125 * 128));

	public static final Material EMERALD =
	    new Material(new float[] { 0.0215f, 0.1745f, 0.0215f, 1 },
	                 new float[] { 0.07568f, 0.61424f, 0.07568f, 1 },
	                 new float[] { 0.633f, 0.727811f, 0.633f, 1 },
	                 new float[] { 0, 0, 0, 1 },(float) (0.6 * 128));

	public static final Material RED_PLASTIC =
	    new Material(new float[] { 0, 0, 0, 1 },new float[] { 0.5f, 0, 0, 1 },
	                 new float[] { 0.7f, 0.6f, 0.6f, 1 },
	                 new float[] { 0, 0, 0, 1 },(float) (0.25 * 128));

	public static final Material CHROME =
	    new Material(new float[] { 0.25f, 0.25f, 0.25f, 1 },
	                 new float[] { 0.4f, 0.4f, 0.4f, 1 },
	                 new float[] { 0.774597f, 0.774597f, 0.774597f, 1 },
	                 new float[] { 0, 0, 0, 1 },(float) (0.6 * 128));

	public static final Material GOLD =
	    new Material(new float[] { 0.24725f, 0.1995f, 0.0745f, 1 },// ambient
	                 new float[] { 0.75164f, 0.60648f, 0.22648f, 1 },// diffuse
	                 new float[] { 0.628281f, 0.555802f, 0.366065f, 1 },// specular
	                 new float[] { 0, 0, 0, 1 }, // emission
	                 (float) (0.6 * 128)); // shininess

	public static final Material OBSIDIAN =
	    new Material(new float[] { 0.05375f, 0.05f, 0.06625f, 1 },
	                 new float[] { 0.18275f, 0.17f, 0.22525f, 1 },
	                 new float[] { 0.332741f, 0.328634f, 0.346435f, 1 },
	                 new float[] { 0, 0, 0, 1 },(float) (0.3 * 128));

	public static final Material PEARL =
	    new Material(new float[] { 0.25f, 0.20725f, 0.2072f, 1 },// ambient
	                 new float[] { 1, 0.829f, 0.829f, 1 },// diffuse
	                 new float[] { 0.296648f, 0.296648f, 0.296648f, 1 },// specular
	                 new float[] { 0, 0, 0, 1 }, // emission
	                 (float) (0.088 * 128)); // shininess;;

	public static final Material RUBY =
	    new Material(new float[] { 0.1745f, 0.01175f, 0.01175f, 1 },
	                 new float[] { 0.61424f, 0.04136f, 0.04136f, 1 },
	                 new float[] { 0.727811f, 0.626959f, 0.626959f, 1 },
	                 new float[] { 0, 0, 0, 1 },(float) (0.6 * 128));

	public static final Material WHITE_RUBBER =
	    new Material(new float[] { 0.05f, 0.05f, 0.05f, 1 },
	                 new float[] { 0.5f, 0.5f, 0.5f, 1 },
	                 new float[] { 0.7f, 0.7f, 0.7f, 1 },
	                 new float[] { 0, 0, 0, 1 },(float) (0.078125 * 128));

}
