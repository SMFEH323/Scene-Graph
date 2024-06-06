package main;

/**
 * @author Sayf Elhawary and Mohammad Yassin
 */
/**
 * A Camera object encapsulates the information needed to define a viewing
 * transform and a projection for an OpenGL context. The apply method can be
 * called to applied this information to a context. The default view is from the
 * point (0,0,30), looking at (0,0,0), with (0,1,0) pointing upwards on the
 * screen. The default projection is a perspective projection. The x and y
 * limits on the screen include at least -5 to 5. Limits in either the x or y
 * direction will be expanded if necessary to match the aspect ratio of the
 * screen. And the view volume extends from -10 to 10 along the z-axis. Only the
 * default constructor exists. Non-default properties must be set by calling
 * methods. The camera comes along with a simulated trackball that lets the user
 * rotate the view by dragging on the drawing surface. See the
 * installTrackball() method.
 */
public class Camera {

	private double eyex, eyey, eyez = 30;
	private double refx, refy, refz;
	private double upx, upy = 1, upz;

	private double xminRequested = -5, xmaxRequested = 5;
	private double yminRequested = -5, ymaxRequested = 5;
	private double zmin = -10, zmax = 10;
	private boolean orthographic;
	private boolean preserveAspect = true;

	public double xminActual, xmaxActual, yminActual, ymaxActual;

	public boolean getOrthographic () {
		return orthographic;
	}

	/**
	 * Determine whether the projection is orthographic or perspective. The
	 * default is perspective.
	 * 
	 * @param orthographic
	 *          set to true for orthographic projection and to false for
	 *          perspective projection.
	 */
	public void setOrthographic ( boolean orthographic ) {
		this.orthographic = orthographic;
	}

	public boolean getPreserveAspect () {
		return preserveAspect;
	}

	/**
	 * Determine whether the xy-limits should be adjusted to match the aspect
	 * ratio of the display area. The default is true.
	 */
	public void setPreserveAspect ( boolean preserveAspect ) {
		this.preserveAspect = preserveAspect;
	}

	/**
	 * Set the limits of the view volume. The limits are set with respect to the
	 * viewing coordinates. That is, the view center is assumed to be at the point
	 * (0,0) in the plane of the screen. The view up vector (more precisely, its
	 * projection onto the screen) points upwards on the screen. The z-axis is
	 * perpendicular to the screen, with the positive direction of the z-axis
	 * pointing out of the screen. In this coordinate system, xmin and xmax give
	 * the horizontal limits on the screen, ymin and ymax give the vertical limits
	 * on the screen, and zmin and zmax give the limits of the view volume along
	 * the z-axis. (Note that this is NOT exactly the same as the parameters in
	 * either glOrtho or glFrustum! Most important to note is that zmin and zmax
	 * are given with reference to the view center, not the eye.) Note that
	 * xmin/xmax or ymin/ymax might be adjusted to match the aspect ratio of the
	 * display area.
	 */
	public void setLimits ( double xmin, double xmax, double ymin, double ymax,
	                        double zmin, double zmax ) {
		xminRequested = xminActual = xmin;
		xmaxRequested = xmaxActual = xmax;
		yminRequested = yminActual = ymin;
		ymaxRequested = ymaxActual = ymax;
		this.zmin = zmin;
		this.zmax = zmax;
	}

	/**
	 * A convenience method for calling
	 * setLimits(-limit,limit,-limit,limit,-2*limit,2*limit)
	 */
	public void setScale ( double limit ) {
		setLimits(-limit,limit,-limit,limit,-2 * limit,2 * limit);
	}

	/**
	 * Returns the view limits. The return value is an array that contains the
	 * same data as the parameters to setLimits(). Note that the returned values
	 * included the originally requested xmin/xmax and ymin/ymax, and NOT values
	 * that have been adjusted to reflect the aspect ratio of the display area.
	 */
	public double[] getLimits () {
		return new double[] { xminRequested, xmaxRequested, yminRequested,
		                      ymaxRequested, zmin, zmax };
	}

	/**
	 * Returns the actual xmin, xmax, ymin, ymax limits that were used when the
	 * apply method was most recently called. These are the limits after they
	 * were, possibly, adjusted to match the aspect ratio of the display. If apply
	 * has not been called since the limits were set, then the return value
	 * contains the unadjusted, requested limits.
	 */
	public double[] getActualXYLimits () {
		return new double[] { xminActual, xmaxActual, yminActual, ymaxActual };
	}

	/**
	 * Set the information for the viewing transformation. The view will be set in
	 * the apply method with a call to
	 * gluLookAt(eyeX,eyeY,eyeZ,viewCenterX,viewCenterY,viewCenterZ,viewUpX,viewUpY,viewUpZ)
	 */
	public void lookAt ( double eyeX, double eyeY, double eyeZ,
	                     double viewCenterX, double viewCenterY,
	                     double viewCenterZ, double viewUpX, double viewUpY,
	                     double viewUpZ ) {
		eyex = eyeX;
		eyey = eyeY;
		eyez = eyeZ;
		refx = viewCenterX;
		refy = viewCenterY;
		refz = viewCenterZ;
		upx = viewUpX;
		upy = viewUpY;
		upz = viewUpZ;
	}

	/**
	 * Returns the view information -- the 9 parameters of lookAt(), in an array.
	 */
	public double[] getViewParameters () {
		return new double[] { eyex, eyey, eyez, refx, refy, refz, upx, upy, upz };
	}

	/**
	 * Apply the camera to an OpenGL context. This method completely replaces the
	 * projection and the modelview transformation in the context. It sets these
	 * transformations to the identity and then applies the view and projection
	 * represented by the camera. This method is meant to be called at the
	 * begining of the display method and should replace any other means of
	 * setting the projection and view.
	 */
	public void apply ( Renderer renderer ) {
		renderer.applyCamera(this);
	}

	/**
	 * Calculates the norm (length) of a given 3D vector.
	 * 
	 * @param v
	 *          A 3D vector.
	 * @return The norm of the vector.
	 * @throws NumberFormatException
	 *           if the vector length is zero, undefined, or infinite.
	 */
	public double norm ( double[] v ) {
		double norm2 = v[0] * v[0] + v[1] * v[1] + v[2] * v[2];
		if ( Double.isNaN(norm2) || Double.isInfinite(norm2) || norm2 == 0 )
		  throw new NumberFormatException("Vector length zero, undefined, or infinite.");
		return Math.sqrt(norm2);
	}

	/**
	 * Normalizes a 3D vector.
	 * 
	 * @param v
	 */
	private void normalize ( double[] v ) {
		double norm = norm(v);
		v[0] /= norm;
		v[1] /= norm;
		v[2] /= norm;
	}

	/**
	 * Applies a transvection operation between two vectors.
	 * 
	 * @param e1
	 * @param e2
	 */
	public void applyTransvection ( double[] e1, double[] e2 ) {
		// rotate vector e1 onto e2; must be 3D *UNIT* vectors.
		double[] zDirection =
		    new double[] { eyex - refx, eyey - refy, eyez - refz };
		double viewDistance = norm(zDirection);
		normalize(zDirection);
		double[] yDirection = new double[] { upx, upy, upz };
		double upLength = norm(yDirection);
		double proj = yDirection[0] * zDirection[0] + yDirection[1] * zDirection[1]
		    + yDirection[2] * zDirection[2];
		yDirection[0] = yDirection[0] - proj * zDirection[0];
		yDirection[1] = yDirection[1] - proj * zDirection[1];
		yDirection[2] = yDirection[2] - proj * zDirection[2];
		normalize(yDirection);
		double[] xDirection = new double[] {
		                                     yDirection[1] * zDirection[2]
		                                         - yDirection[2] * zDirection[1],
		                                     yDirection[2] * zDirection[0]
		                                         - yDirection[0] * zDirection[2],
		                                     yDirection[0] * zDirection[1]
		                                         - yDirection[1] * zDirection[0] };
		e1 = transformToViewCoords(e1,xDirection,yDirection,zDirection);
		e2 = transformToViewCoords(e2,xDirection,yDirection,zDirection);
		double[] e = new double[] { e1[0] + e2[0], e1[1] + e2[1], e1[2] + e2[2] };
		normalize(e);
		double[] temp = new double[3];
		reflectInAxis(e,zDirection,temp);
		reflectInAxis(e1,temp,zDirection);
		reflectInAxis(e,xDirection,temp);
		reflectInAxis(e1,temp,xDirection);
		reflectInAxis(e,yDirection,temp);
		reflectInAxis(e1,temp,yDirection);
		eyex = refx + viewDistance * zDirection[0];
		eyey = refy + viewDistance * zDirection[1];
		eyez = refz + viewDistance * zDirection[2];
		upx = upLength * yDirection[0];
		upy = upLength * yDirection[1];
		upz = upLength * yDirection[2];
	}

	/**
	 * Reflects a vector in a given axis.
	 * 
	 * @param axis
	 * @param source
	 * @param destination
	 */
	private void reflectInAxis ( double[] axis, double[] source,
	                             double[] destination ) {
		double s =
		    2 * (axis[0] * source[0] + axis[1] * source[1] + axis[2] * source[2]);
		destination[0] = s * axis[0] - source[0];
		destination[1] = s * axis[1] - source[1];
		destination[2] = s * axis[2] - source[2];
	}

	/**
	 * Transforms a vector to view coordinates.
	 * 
	 * @param v
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	private double[] transformToViewCoords ( double[] v, double[] x, double[] y,
	                                         double[] z ) {
		double[] w = new double[3];
		w[0] = v[0] * x[0] + v[1] * y[0] + v[2] * z[0];
		w[1] = v[0] * x[1] + v[1] * y[1] + v[2] * z[1];
		w[2] = v[0] * x[2] + v[1] * y[2] + v[2] * z[2];
		return w;
	}

//0 for orthographic, 1 for perspective
	public static final float[] FRONT = new float[] { -5, 5, -5, 5, -5, 5, 0 };

	public static final float[] SIDE = new float[] { -5, 5, -5, 5, -5, 5, 0 };

	public static final float[] PLAN = new float[] { -5, 5, -5, 5, -5, 5, 0 };

	public static final float[] ISOMETRIC =
	    new float[] { -7, 7, -7, 7, -7, 7, 0 };

	public static final float[] DIMETRIC = new float[] { -7, 7, -7, 7, -7, 7, 0 };

	public static final float[] TRIMETRIC =
	    new float[] { -7, 7, -7, 7, -7, 7, 0 };

	public static final float[] ONE_POINT =
	    new float[] { -1, 1, -1, 1, 1, 10, 1 };

	public static final float[] TWO_POINT =
	    new float[] { -1, 1, -1, 1, 1, 10, 1 };

	public static final float[] THREE_POINT =
	    new float[] { -1, 1, -1, 1, 1, 10, 1 };

}
