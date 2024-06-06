package nodes;

import main.Renderer;
import main.Tex;

/**
 * @author Sayf Elhawary
 */
public class TextureNode extends PartialScene {

	private Tex tex;

	/**
	 * Constructs a TextureNode with a specific texture.
	 * 
	 * @param tex
	 *          The texture to be associated with this node.
	 */
	public TextureNode ( Tex tex ) {
		super();
		this.tex = tex;
	}

	/**
	 * Retrieves the texture associated with this node.
	 * 
	 * @return The current texture object.
	 */
	public Tex getTexture () {
		return tex;
	}

	/**
	 * Sets or updates the texture associated with this node.
	 * 
	 * @param tex
	 *          The new texture to be associated with this node.
	 */
	public void setTexture ( Tex tex ) {
		this.tex = tex;
	}

	/**
	 * Applies the texture to the renderer. This method is called during the
	 * rendering process, and it sets up the texturing for objects that are
	 * subsequently rendered.
	 * 
	 * @param renderer
	 *          The renderer to which the texture is applied.
	 */
	public void draw ( Renderer renderer ) {
		tex.apply(renderer);
	}
}
