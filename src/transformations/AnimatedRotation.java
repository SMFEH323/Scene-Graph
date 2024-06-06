package transformations;
import main.Renderer;

/**
* AnimatedRotation class extends the Rotation class.
* This class is responsible for animated rotations.
* 
* @author Sayf Elhawary
*/
public class AnimatedRotation extends Rotation {

 /**
  * Constructor for the AnimatedRotation class.
  *
  * @param angle The angle of rotation.
  * @param x     The x-coordinate for the rotation axis.
  * @param y     The y-coordinate for the rotation axis.
  * @param z     The z-coordinate for the rotation axis.
  * @param tex   A boolean indicating whether texture should be applied or not.
  */
 public AnimatedRotation(float angle, float x, float y, float z, boolean tex) {
     // Calls the constructor of the parent class (Rotation) with the provided parameters.
     super(angle, x, y, z, tex);
 }

 /**
  * Apply the animated rotation transformation to the renderer.
  *
  * @param renderer The renderer where the rotation will be applied.
  */
 public void apply(Renderer renderer) {
     // Calls the 'animateRotate' method from the renderer to apply the rotation.
     renderer.animateRotate(this);
 }
}
