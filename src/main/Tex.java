package main;

/**
 * @author Sayf Elhawary and Mohammad Yassin
 */
public class Tex {

	private String fileName;

	/**
	 * Constructs a new {@code Tex} object with a specified texture file.
	 *
	 * @param fileName
	 *          The name (or path) of the texture file.
	 */
	public Tex ( String fileName ) {
		this.fileName = fileName;
	}

	/**
	 * Retrieves the file name of the texture.
	 *
	 * @return The name (or path) of the texture file.
	 */
	public String getFileName () {
		return fileName;
	}

	/**
	 * Sets or updates the file name of the texture.
	 *
	 * @param fileName
	 *          The new name (or path) of the texture file.
	 */
	public void setFileName ( String fileName ) {
		this.fileName = fileName;
	}

	/**
	 * Applies this texture to a shape or object in the scene using the provided
	 * renderer. This method delegates the task of texture application to the
	 * renderer.
	 *
	 * @param renderer
	 *          The renderer responsible for applying this texture.
	 */
	public void apply ( Renderer renderer ) {
		renderer.applyTexture(this);
	}
}
