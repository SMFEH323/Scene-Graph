package main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import lights.*;
import nodes.Scene;
import shapes.*;
import transformations.*;

/**
 * @author Sayf Elhawary
 */
public class Renderer extends GLJPanel implements GLEventListener {

	private GL2 gl2;
	private GLUT glut = new GLUT();
	private GLU glu_ = new GLU();

	private TrackballMouser trackball; // handles the mouse for the simulated
	                                   // trackball
	private Component trackballComponent; // if non-null, component where
	                                      // trackball is installed

	private Scene scene;

	private Camera camera_;

	public boolean animationRequired;
	public int frameNumber;

	public static final int[] LIGHT_NUMS =
	    new int[] { GL2.GL_LIGHT0, GL2.GL_LIGHT1, GL2.GL_LIGHT2, GL2.GL_LIGHT3,
	                GL2.GL_LIGHT4, GL2.GL_LIGHT5, GL2.GL_LIGHT6, GL2.GL_LIGHT7 };

	public Renderer ( Scene scene ) {

		// create the drawing panel, with the default OpenGL capabilities
		super(new GLCapabilities(null));
		setPreferredSize(new Dimension(700,700));
		this.scene = scene;

		// specify handlers for events on the panel
		addGLEventListener(this); // OpenGL events
		animationRequired = false;
		frameNumber = 0;
	}

	/**
	 * Save the current transformation.
	 * 
	 * @param gl2
	 */
	public void saveTransform () {
		gl2.glPushMatrix();
	}

	/**
	 * Restore the last-saved transformation.
	 * 
	 * @param gl2
	 */
	public void restoreTransform () {
		gl2.glPopMatrix();
	}

	/**
	 * Renders a cube.
	 *
	 * @param cube
	 *          The Cube object to render. The rendering style (textured,
	 *          wireframe, solid) is determined based on the cube's status.
	 */
	public void drawCube ( Cube cube ) {

		if ( cube.getStatus().toUpperCase().equals("TEXTURE") ) {
			TexturedShapes.cube(gl2,cube.getSideLength(),true);
		} else if ( cube.getStatus().toUpperCase().equals("WIRE") ) {
			glut.glutWireCube(cube.getSideLength());
		} else {
			glut.glutSolidCube(cube.getSideLength());
		}

	}

	/**
	 * Renders a sphere.
	 *
	 * @param sphere
	 *          The Sphere object to render. The rendering style (textured,
	 *          wireframe, solid) is determined based on the sphere's status.
	 */
	public void drawSphere ( Sphere sphere ) {

		if ( sphere.getStatus().toUpperCase().equals("TEXTURE") ) {
			GLUquadric shape = glu_.gluNewQuadric();
			glu_.gluQuadricTexture(shape,true);
			glu_.gluSphere(shape,sphere.getRadius(),sphere.getSlices(),
			               sphere.getStacks());
		} else if ( sphere.getStatus().toUpperCase().equals("WIRE") ) {
			glut.glutWireSphere(sphere.getRadius(),sphere.getSlices(),
			                    sphere.getStacks());
		} else {
			glut.glutSolidSphere(sphere.getRadius(),sphere.getSlices(),
			                     sphere.getStacks());
		}

	}

	/**
	 * Renders a cone.
	 *
	 * @param cone
	 *          The Cone object to render. The rendering style (textured,
	 *          wireframe, solid) is determined based on the cone's status.
	 */
	public void drawCone ( Cone cone ) {

		if ( cone.getStatus().toUpperCase().equals("TEXTURE") ) {
			TexturedShapes.uvCone(gl2,cone.getBase(),cone.getHeight(),
			                      cone.getSlices(),cone.getStacks(),
			                      (int) (cone.getSlices() / 2),true);
		} else if ( cone.getStatus().toUpperCase().equals("WIRE") ) {
			glut.glutWireCone(cone.getBase(),cone.getHeight(),cone.getSlices(),
			                  cone.getStacks());
		} else {
			glut.glutSolidCone(cone.getBase(),cone.getHeight(),cone.getSlices(),
			                   cone.getStacks());
		}

	}

	/**
	 * Renders a torus.
	 *
	 * @param torus
	 *          The Torus object to render. The rendering style (textured,
	 *          wireframe, solid) is determined based on the torus's status.
	 */
	public void drawTorus ( Torus torus ) {

		if ( torus.getStatus().toUpperCase().equals("TEXTURE") ) {
			TexturedShapes.uvTorus(gl2,torus.getInnerRadius(),torus.getOuterRadius(),
			                       torus.getSlices(),torus.getStacks() / 2,true);
		} else if ( torus.getStatus().toUpperCase().equals("WIRE") ) {
			glut.glutWireTorus(torus.getInnerRadius(),torus.getOuterRadius(),
			                   torus.getSlices(),torus.getStacks());
		} else {
			glut.glutSolidTorus(torus.getInnerRadius(),torus.getOuterRadius(),
			                    torus.getSlices(),torus.getStacks());
		}

	}

	/**
	 * Renders a cylinder.
	 *
	 * @param cylinder
	 *          The Cylinder object to render. The rendering style (textured,
	 *          wireframe, solid) is determined based on the cylinder's status.
	 */
	public void drawCylinder ( Cylinder cylinder ) {

		if ( cylinder.getStatus().toUpperCase().equals("TEXTURE") ) {
			TexturedShapes.uvCylinder(gl2,cylinder.getRadius(),cylinder.getHeight(),
			                          cylinder.getSlices(),cylinder.getStacks(),
			                          (int) (cylinder.getSlices() / 2),true);
		} else if ( cylinder.getStatus().toUpperCase().equals("WIRE") ) {
			glut.glutWireCylinder(cylinder.getRadius(),cylinder.getHeight(),
			                      cylinder.getSlices(),cylinder.getStacks());
		} else {
			glut.glutSolidCylinder(cylinder.getRadius(),cylinder.getHeight(),
			                       cylinder.getSlices(),cylinder.getStacks());
		}

	}

	/**
	 * Renders a teapot.
	 *
	 * @param teapot
	 *          The Teapot object to render. The rendering style (wireframe,
	 *          solid) is determined based on the teapot's status.
	 */
	public void drawTeapot ( Teapot teapot ) {

		if ( teapot.getStatus().toUpperCase().equals("WIRE") ) {
			glut.glutWireCube(teapot.getSideLength());
		} else {
			glut.glutSolidCube(teapot.getSideLength());
		}

	}

	/**
	 * Renders a complex shape as a polyhedron. This private method is used
	 * internally for rendering complex shapes with detailed faces and edges.
	 *
	 * @param cShape
	 *          The ComplexShape object to render.
	 */
	private void drawPolyhedron ( ComplexShape cShape ) {

		// draw faces
		gl2.glPolygonOffset(1,1);
		gl2.glLineWidth(1);
		gl2.glEnable(GL2.GL_POLYGON_OFFSET_FILL);
		for ( int i = 0 ; i < cShape.faces.length ; i++ ) {
			gl2.glNormal3dv(cShape.faceNormals[i],0);
			gl2.glBegin(GL2.GL_TRIANGLE_FAN);
			for ( int j = 0 ; j < cShape.faces[i].length ; j++ ) {
				if ( cShape.getStatus().toUpperCase().equals("TEXTURE") ) {
					gl2.glTexCoord2d(cShape.texCoords[i][2 * j],
					                 cShape.texCoords[i][2 * j + 1]);
				}
				int vertexNum = cShape.faces[i][j];
				double[] vertexCoords = cShape.vertices[vertexNum];
				gl2.glVertex3dv(vertexCoords,0);
			}
			gl2.glEnd();
		}

		// draw edges

		gl2.glLineWidth(3);
		gl2.glDisable(GL2.GL_POLYGON_OFFSET_FILL);
		gl2.glColor3f(1,1,1);
		for ( int i = 0 ; i < cShape.faces.length ; i++ ) {
			gl2.glNormal3dv(cShape.faceNormals[i],0);
			gl2.glBegin(GL2.GL_LINE_LOOP);
			for ( int j = 0 ; j < cShape.faces[i].length ; j++ ) {
				if ( cShape.getStatus().toUpperCase().equals("TEXTURE") ) {
					gl2.glTexCoord2d(cShape.texCoords[i][2 * j],
					                 cShape.texCoords[i][2 * j + 1]);
				}
				int vertexNum = cShape.faces[i][j]; // Index for vertex j of face i.
				double[] vertexCoords = cShape.vertices[vertexNum]; // The vertex
				                                                    // itself.
				gl2.glVertex3dv(vertexCoords,0);
			}
			gl2.glEnd();
		}

	}

	/**
	 * Renders a complex shape using polygon mesh. This private method is used for
	 * rendering complex shapes with smooth surfaces by utilizing a polygon mesh.
	 *
	 * @param cShape
	 *          The ComplexShape object to render.
	 */
	private void drawPolyMesh ( ComplexShape cShape ) {

		gl2.glNormalPointer(GL2.GL_DOUBLE,0,Buffers
		    .newDirectDoubleBuffer(flatten(cShape.vertexNormals)));
		gl2.glVertexPointer(3,GL2.GL_DOUBLE,0,Buffers
		    .newDirectDoubleBuffer(flatten(cShape.vertices)));
		gl2.glEnableClientState(GL2.GL_NORMAL_ARRAY);
		gl2.glEnableClientState(GL2.GL_VERTEX_ARRAY);

		if ( cShape.getStatus().toUpperCase().equals("TEXTURE") ) {
			gl2.glTexCoordPointer(2,GL2.GL_DOUBLE,0,Buffers
			    .newDirectDoubleBuffer(flatten(cShape.texCoords)));
			gl2.glEnableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
		}

		gl2.glLineWidth(2);
		gl2.glPolygonOffset(1,1);
		gl2.glEnable(GL2.GL_POLYGON_OFFSET_FILL);

		for ( int i = 0 ; i < cShape.faces.length ; i++ ) {
			gl2.glDrawElements(GL2.GL_TRIANGLE_FAN,cShape.faces[i].length,
			                   GL2.GL_UNSIGNED_INT,
			                   Buffers.newDirectIntBuffer(cShape.faces[i]));
		}
		gl2.glDisable(GL2.GL_POLYGON_OFFSET_FILL);

	}

	/**
	 * Renders a complex shape. This method determines the rendering style (smooth
	 * mesh or polyhedron) based on the shape's status and delegates to the
	 * appropriate private method for actual rendering.
	 *
	 * @param cShape
	 *          The ComplexShape object to render.
	 */
	public void drawComplexShape ( ComplexShape cShape ) {
		if ( cShape.getStatus().toUpperCase().equals("SMOOTH") ) {
			drawPolyMesh(cShape);
		} else {
			drawPolyhedron(cShape);
		}
	}

	/**
	 * Applies the given material properties to the current rendering context.
	 * This method sets various material properties like ambient, diffuse,
	 * specular, and emissive colors, as well as the shininess factor, for both
	 * front and back faces of the geometries.
	 *
	 * @param material
	 *          The material object containing properties to be applied.
	 */
	public void applyMaterial ( Material material ) {
		gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK,GL2.GL_AMBIENT,material.getAmbient(),
		                 0);
		gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK,GL2.GL_DIFFUSE,material.getDiffuse(),
		                 0);
		gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK,GL2.GL_SPECULAR,
		                 material.getSpecular(),0);
		gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK,GL2.GL_EMISSION,
		                 material.getEmissive(),0);
		gl2.glMaterialf(GL2.GL_FRONT_AND_BACK,GL2.GL_SHININESS,
		                material.getShininess());
	}

	/**
	 * Applies the properties of a point light to the current rendering context.
	 * This method sets up a point light source in the scene, configuring its
	 * ambient, diffuse, specular properties, and its position.
	 *
	 * @param pLight
	 *          The point light object with its configuration.
	 */
	public void applyPointLight ( PointLight pLight ) {
		float[] pos = { pLight.getPosition()[0], pLight.getPosition()[1],
		                pLight.getPosition()[2], 1 };
		gl2.glLightfv(pLight.getLightId(),GL2.GL_AMBIENT,pLight.getAmbient(),0);
		gl2.glLightfv(pLight.getLightId(),GL2.GL_DIFFUSE,pLight.getDiffuse(),0);
		gl2.glLightfv(pLight.getLightId(),GL2.GL_SPECULAR,pLight.getSpecular(),0);
		gl2.glLightfv(pLight.getLightId(),GL2.GL_POSITION,pos,0);

		gl2.glEnable(pLight.getLightId());
	}

	/**
	 * Applies the properties of a directional light to the current rendering
	 * context. Directional lights simulate light coming from a specific
	 * direction, rather than a point in space. This method configures the light's
	 * ambient, diffuse, and specular properties, as well as its direction.
	 *
	 * @param dLight
	 *          The directional light object with its configuration.
	 */
	public void applyDirectionalLight ( DirectionalLight dLight ) {
		float[] pos = { dLight.getPosition()[0], dLight.getPosition()[1],
		                dLight.getPosition()[2], 0 };
		gl2.glLightfv(dLight.getLightId(),GL2.GL_AMBIENT,dLight.getAmbient(),0);
		gl2.glLightfv(dLight.getLightId(),GL2.GL_DIFFUSE,dLight.getDiffuse(),0);
		gl2.glLightfv(dLight.getLightId(),GL2.GL_SPECULAR,dLight.getSpecular(),0);
		gl2.glLightfv(dLight.getLightId(),GL2.GL_POSITION,pos,0);

		gl2.glEnable(dLight.getLightId());
	}

	/**
	 * Applies the properties of a spot light to the current rendering context.
	 * Spot lights emit light in a cone shape. This method sets the spot light's
	 * ambient, diffuse, specular properties, position, direction, cutoff angle,
	 * and exponent.
	 *
	 * @param sLight
	 *          The spot light object with its configuration.
	 */
	public void applySpotLight ( SpotLight sLight ) {
		float[] pos = { sLight.getPosition()[0], sLight.getPosition()[1],
		                sLight.getPosition()[2], 0 };
		gl2.glLightfv(sLight.getLightId(),GL2.GL_AMBIENT,sLight.getAmbient(),0);
		gl2.glLightfv(sLight.getLightId(),GL2.GL_DIFFUSE,sLight.getDiffuse(),0);
		gl2.glLightfv(sLight.getLightId(),GL2.GL_SPECULAR,sLight.getSpecular(),0);
		gl2.glLightfv(sLight.getLightId(),GL2.GL_POSITION,pos,0);
		gl2.glLightfv(sLight.getLightId(),GL2.GL_SPOT_DIRECTION,
		              sLight.getDirection(),0);
		gl2.glLightf(sLight.getLightId(),GL2.GL_SPOT_CUTOFF,sLight.getCutoff());
		gl2.glLightf(sLight.getLightId(),GL2.GL_SPOT_EXPONENT,sLight.getExponent());

		gl2.glEnable(sLight.getLightId());
	}

	/**
	 * Applies a texture to the current rendering context. This method loads a
	 * texture from a file and binds it to the current context, setting texture
	 * parameters such as wrap mode.
	 *
	 * @param texture
	 *          The texture object containing the texture's file name.
	 */
	public void applyTexture ( Tex texture ) {
		try {
			Texture tex = TextureIO.newTexture(new File(texture.getFileName()),false);
			tex.setTexParameteri(gl2,GL2.GL_TEXTURE_WRAP_S,GL2.GL_REPEAT);
			tex.setTexParameteri(gl2,GL2.GL_TEXTURE_WRAP_T,GL2.GL_REPEAT);
			tex.enable(gl2);
			tex.bind(gl2);
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	/**
	 * Applies the properties of a camera to the current rendering context. This
	 * method configures the camera's view parameters, including its position,
	 * orientation, and projection type (orthographic or perspective). It also
	 * handles aspect ratio correction if necessary.
	 *
	 * @param camera
	 *          The camera object with its configuration.
	 */
	public void applyCamera ( Camera camera ) {
		camera_ = camera;
		installTrackball();
		int[] viewport = new int[4];
		gl2.glGetIntegerv(GL2.GL_VIEWPORT,viewport,0);
		camera.xminActual = camera.getLimits()[0];
		camera.xmaxActual = camera.getLimits()[1];
		camera.yminActual = camera.getLimits()[2];
		camera.ymaxActual = camera.getLimits()[3];
		if ( camera.getPreserveAspect() ) {
			double viewWidth = viewport[2];
			double viewHeight = viewport[3];
			double windowWidth =
			    camera.getActualXYLimits()[1] - camera.getActualXYLimits()[0];
			double windowHeight =
			    camera.getActualXYLimits()[3] - camera.getActualXYLimits()[2];
			double aspect = viewHeight / viewWidth;
			double desired = windowHeight / windowWidth;
			if ( desired > aspect ) { // expand width
				double extra = (desired / aspect - 1.0)
				    * (camera.getActualXYLimits()[1] - camera.getActualXYLimits()[0])
				    / 2.0;
				camera.xminActual -= extra;
				camera.xmaxActual += extra;
			} else if ( aspect > desired ) {
				double extra = (aspect / desired - 1.0)
				    * (camera.getActualXYLimits()[3] - camera.getActualXYLimits()[2])
				    / 2.0;
				camera.yminActual -= extra;
				camera.ymaxActual += extra;
			}
		}
		gl2.glMatrixMode(GL2.GL_PROJECTION);
		gl2.glLoadIdentity();
		double viewDistance =
		    camera.norm(new double[] {
		                               camera.getViewParameters()[3]
		                                   - camera.getViewParameters()[0],
		                               camera.getViewParameters()[4]
		                                   - camera.getViewParameters()[1],
		                               camera.getViewParameters()[5]
		                                   - camera.getViewParameters()[2] });
		if ( camera.getOrthographic() ) {
			gl2.glOrtho(camera.getActualXYLimits()[0],camera.getActualXYLimits()[1],
			            camera.getActualXYLimits()[2],camera.getActualXYLimits()[3],
			            viewDistance - camera.getLimits()[5],
			            viewDistance - camera.getLimits()[4]);
		} else {
			double near = viewDistance - camera.getLimits()[5];
			if ( near < 0.1 ) near = 0.1;
			double centerx =
			    (camera.getActualXYLimits()[0] + camera.getActualXYLimits()[1]) / 2;
			double centery =
			    (camera.getActualXYLimits()[2] + camera.getActualXYLimits()[3]) / 2;
			double newwidth = (near / viewDistance)
			    * (camera.getActualXYLimits()[1] - camera.getActualXYLimits()[0]);
			double newheight = (near / viewDistance)
			    * (camera.getActualXYLimits()[3] - camera.getActualXYLimits()[2]);
			double x1 = centerx - newwidth / 2;
			double x2 = centerx + newwidth / 2;
			double y1 = centery - newheight / 2;
			double y2 = centery + newheight / 2;
			gl2.glFrustum(x1,x2,y1,y2,near,viewDistance - camera.getLimits()[4]);
		}
		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glLoadIdentity();
		glu_.gluLookAt(camera.getViewParameters()[0],camera.getViewParameters()[1],
		               camera.getViewParameters()[2],camera.getViewParameters()[3],
		               camera.getViewParameters()[4],camera.getViewParameters()[5],
		               camera.getViewParameters()[6],camera.getViewParameters()[7],
		               camera.getViewParameters()[8]);
	}

	/**
	 * Installs a simulated trackball for an OpenGL component, which must be the
	 * same component on which this camera is used. The user can rotate the view
	 * by dragging on the component. This will only work if the camera's apply()
	 * method is called at the start of the component's display function to set up
	 * the viewing and projection transformations. The camera words by modifying
	 * the camera's viewing parameters.
	 * 
	 * @param c
	 *          The OpenGL drawing surface where the trackball will be installed.
	 *          This is probably a GLJPanel or GLCanvas. Passing a null as the
	 *          parameter will remove the trackball from the component (if it has
	 *          been installed there).
	 */
	public void installTrackball () {
		if ( trackballComponent != null && trackballComponent != this ) {
			trackballComponent.removeMouseListener(trackball);
		}
		if ( trackballComponent == this ) {
			return;
		}
		trackballComponent = this;
		if ( trackballComponent == null ) {
			return;
		}
		if ( trackball == null ) {
			trackball = new TrackballMouser();
		}
		trackballComponent.addMouseListener(trackball);
	}

	/**
	 * Applies a translation transformation to the current scene. This method
	 * supports both regular and texture transformations.
	 * 
	 * @param t
	 *          The Translation object containing the translation parameters.
	 */
	public void translate ( Translation t ) {
		if ( t.getTex() ) {
			gl2.glMatrixMode(GL2.GL_TEXTURE);
			gl2.glLoadIdentity();
			gl2.glTranslatef(t.getX(),t.getY(),t.getZ());

			gl2.glMatrixMode(GL2.GL_MODELVIEW);
		} else {
			gl2.glTranslatef(t.getX(),t.getY(),t.getZ());
		}

	}

	/**
	 * Applies a rotation transformation to the current scene. This method
	 * supports both regular and texture transformations.
	 * 
	 * @param r
	 *          The Rotation object containing the rotation parameters.
	 */
	public void rotate ( Rotation r ) {
		if ( r.getTex() ) {
			gl2.glMatrixMode(GL2.GL_TEXTURE);
			gl2.glLoadIdentity();
			gl2.glRotatef(r.getAngle(),r.getX(),r.getY(),r.getZ());

			gl2.glMatrixMode(GL2.GL_MODELVIEW);
		} else {
			gl2.glRotatef(r.getAngle(),r.getX(),r.getY(),r.getZ());
		}

	}

	/**
	 * Applies a scaling transformation to the current scene. This method supports
	 * both regular and texture transformations.
	 * 
	 * @param s
	 *          The Scale object containing the scaling parameters.
	 */
	public void scale ( Scale s ) {
		if ( s.getTex() ) {
			gl2.glMatrixMode(GL2.GL_TEXTURE);
			gl2.glLoadIdentity();
			gl2.glScalef(s.getX(),s.getY(),s.getZ());

			gl2.glMatrixMode(GL2.GL_MODELVIEW);
		} else {
			gl2.glScalef(s.getX(),s.getY(),s.getZ());
		}

	}

	/**
	 * Performs an animated translation over time. This method calculates new
	 * positions based on the frame number and applies a translation.
	 * 
	 * @param t
	 *          The AnimatedTranslation object with the translation parameters.
	 */
	public void animateTranslate ( AnimatedTranslation t ) {
		animationRequired = true;
		float newX = 0;
		float newY = 0;
		float newZ = 0;
		float smallest = Math.min(Math.min(t.getX(),t.getY()),t.getZ());
		if ( t.getX() > 0 ) {
			newX = (frameNumber * (t.getX() / smallest)) % t.getX();
		} else if ( t.getX() < 0 ) {
			newX = (-frameNumber * (t.getX() / smallest)) % t.getX();
		}

		if ( t.getY() > 0 ) {
			newY = (frameNumber * (t.getY() / smallest)) % t.getY();
		} else if ( t.getY() < 0 ) {
			newY = (frameNumber * (t.getY() / smallest)) % t.getY();
		}

		if ( t.getZ() > 0 ) {
			newZ = (frameNumber * (t.getZ() / smallest)) % t.getZ();
		} else if ( t.getZ() < 0 ) {
			newZ = (frameNumber * (t.getZ() / smallest)) % t.getZ();
		}

		translate(new Translation(newX,newY,newZ,t.getTex()));

	}

	/**
	 * Performs an animated rotation over time. This method calculates new
	 * rotation angles based on the frame number and applies a rotation.
	 * 
	 * @param r
	 *          The AnimatedRotation object with the rotation parameters.
	 */
	public void animateRotate ( AnimatedRotation r ) {
		animationRequired = true;
		float newAngle = 0;
		if ( r.getAngle() > 0 ) {
			newAngle = (frameNumber) % r.getAngle();
		} else if ( r.getAngle() < 0 ) {
			newAngle = -(frameNumber) % r.getAngle();
		}

		rotate(new Rotation(newAngle,r.getX(),r.getY(),r.getZ(),r.getTex()));

	}

	/**
	 * Performs an animated scaling over time. This method calculates new scaling
	 * factors based on the frame number and applies a scaling.
	 * 
	 * @param s
	 *          The AnimatedScale object with the scaling parameters.
	 */
	public void animateScale ( AnimatedScale s ) {
		animationRequired = true;
		float newX = 1;
		float newY = 1;
		float newZ = 1;
		float smallest = Math.min(Math.min(s.getX(),s.getY()),s.getZ());
		if ( s.getX() != 0 ) {
			newX = (frameNumber * (s.getX() / smallest)) % s.getX() + 1;
		} else if ( s.getX() < 0 ) {
			newX = (frameNumber * (s.getX() / smallest)) % s.getX() + 1;
		}

		if ( s.getY() != 0 ) {
			newY = (frameNumber * (s.getY() / smallest)) % s.getY() + 1;
		} else if ( s.getY() < 0 ) {
			newY = (frameNumber * (s.getY() / smallest)) % s.getY() + 1;
		}

		if ( s.getZ() != 0 ) {
			newZ = (frameNumber * (s.getZ() / smallest)) % s.getZ() + 1;
		} else if ( s.getZ() < 0 ) {
			newZ = (frameNumber * (s.getZ() / smallest)) % s.getZ() + 1;
		}

		scale(new Scale(newX,newY,newZ,s.getTex()));
	}

	/**
	 * Sets up the initial state for the renderer. This includes enabling depth
	 * testing, normalization, lighting, and setting up a light source.
	 */
	public void setup () {

		// depth test, for 3D
		gl2.glEnable(GL2.GL_DEPTH_TEST);

		// other configuration
		gl2.glEnable(GL2.GL_NORMALIZE);
		gl2.glEnable(GL2.GL_LIGHTING);
		gl2.glEnable(GL2.GL_LIGHT0);

	}

	/**
	 * Triggers the rendering process of the scene. This method should be called
	 * to start the drawing process.
	 */
	public void draw () {
		scene.draw(this);
	}

	/**
	 * Clears the display to prepare for the next frame rendering. This method
	 * resets the color buffer and the depth buffer.
	 */
	public void clearDisplay () {
		gl2.glClearColor(scene.getBGColors()[0],scene.getBGColors()[1],
		                 scene.getBGColors()[2],scene.getBGColors()[3]);
		gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
	}

	/**
	 * Return a 2D array as a 1D array containing the elements in row-major order.
	 */
	public double[] flatten ( double[][] array2d ) {
		int n = 0;
		for ( int i = 0 ; i < array2d.length ; i++ ) {
			n += array2d[i].length;
		}

		double[] array = new double[n];
		for ( int i = 0, k = 0 ; i < array2d.length ; i++ ) {
			for ( int j = 0 ; j < array2d[i].length ; j++ ) {
				array[k] = array2d[i][j];
				k++;
			}
		}

		return array;
	}

	/**
	 * Return a 2D array as a 1D array containing the elements in row-major order.
	 */
	public int[] flatten ( int[][] array2d ) {
		int n = 0;
		for ( int i = 0 ; i < array2d.length ; i++ ) {
			n += array2d[i].length;
		}

		int[] array = new int[n];
		for ( int i = 0, k = 0 ; i < array2d.length ; i++ ) {
			for ( int j = 0 ; j < array2d[i].length ; j++ ) {
				array[k] = array2d[i][j];
				k++;
			}
		}

		return array;
	}

	/**
	 * Return a 2D array as a 1D array containing the elements in row-major order.
	 */
	public float[] flatten ( float[][] array2d ) {
		int n = 0;
		for ( int i = 0 ; i < array2d.length ; i++ ) {
			n += array2d[i].length;
		}

		float[] array = new float[n];
		for ( int i = 0, k = 0 ; i < array2d.length ; i++ ) {
			for ( int j = 0 ; j < array2d[i].length ; j++ ) {
				array[k] = array2d[i][j];
				k++;
			}
		}

		return array;
	}

	/**
	 * Set the current color (r,g,b). Color values are between 0 and 1.
	 * 
	 * @param gl2
	 * @param r
	 * @param g
	 * @param b
	 */
	public void color ( float r, float g, float b ) {
		gl2.glColor3f(r,g,b);
	}

	/**
	 * Initializes and starts the animation process for the scene. This method
	 * sets up an FPSAnimator to handle the animation at a specified frames per
	 * second rate.
	 */
	public void animate () {
		FPSAnimator animator = new FPSAnimator(this,60,true);
		animator.start();
	}

	public void display ( GLAutoDrawable drawable ) {
		gl2 = drawable.getGL().getGL2();
		clearDisplay();

		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glLoadIdentity();

		draw();
		if ( animationRequired ) {
			frameNumber++;
		}
		gl2.glFlush();
	}

	public void dispose ( GLAutoDrawable drawable ) {}

	public void init ( GLAutoDrawable drawable ) {
		gl2 = drawable.getGL().getGL2();
		setup();

	}

	public void reshape ( GLAutoDrawable drawable, int x, int y, int width,
	                      int height ) {}

	private class TrackballMouser implements MouseListener, MouseMotionListener {

		private boolean dragging;
		private double[] prevRay;

		/**
		 * Called when the mouse button is pressed. Starts the dragging process and
		 * calculates the initial ray from the mouse point.
		 *
		 * @param e
		 *          The MouseEvent object containing details about the mouse event.
		 */
		public void mousePressed ( MouseEvent e ) {
			if ( dragging ) return;
			dragging = true;
			prevRay = mousePointToRay(e.getX(),e.getY());
			trackballComponent.addMouseMotionListener(this);
		}

		/**
		 * Called when the mouse button is released. Ends the dragging process and
		 * stops listening for mouse motion events.
		 *
		 * @param e
		 *          The MouseEvent object containing details about the mouse event.
		 */
		public void mouseReleased ( MouseEvent e ) {
			if ( !dragging ) return;
			dragging = false;
			trackballComponent.removeMouseMotionListener(this);
		}

		/**
		 * Called when the mouse is dragged. Calculates the new ray from the current
		 * mouse position and updates the camera's orientation.
		 *
		 * @param e
		 *          The MouseEvent object containing details about the mouse event.
		 */
		public void mouseDragged ( MouseEvent e ) {
			if ( !dragging ) return;
			double[] thisRay = mousePointToRay(e.getX(),e.getY());
			camera_.applyTransvection(prevRay,thisRay);
			prevRay = thisRay;
			trackballComponent.repaint();
		}

		/**
		 * Converts a 2D mouse point into a 3D ray for trackball rotation.
		 *
		 * @param x
		 *          The x-coordinate of the mouse point.
		 * @param y
		 *          The y-coordinate of the mouse point.
		 * @return A normalized 3D vector representing the ray from the mouse point.
		 */
		private double[] mousePointToRay ( int x, int y ) {
			double dx, dy, dz, norm;
			int centerX = trackballComponent.getWidth() / 2;
			int centerY = trackballComponent.getHeight() / 2;
			double scale = 0.8 * Math.min(centerX,centerY);
			dx = (x - centerX);
			dy = (centerY - y);
			norm = Math.sqrt(dx * dx + dy * dy);
			if ( norm >= scale ) dz = 0;
			else dz = Math.sqrt(scale * scale - dx * dx - dy * dy);
			double length = Math.sqrt(dx * dx + dy * dy + dz * dz);
			return new double[] { dx / length, dy / length, dz / length };
		}

		public void mouseClicked ( MouseEvent e ) {}

		public void mouseEntered ( MouseEvent e ) {}

		public void mouseExited ( MouseEvent e ) {}

		public void mouseMoved ( MouseEvent e ) {}

	}

}
