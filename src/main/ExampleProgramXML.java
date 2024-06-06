package main;

import javax.swing.JFrame;

import lights.DirectionalLight;
import lights.PointLight;
import nodes.CameraNode;
import nodes.LightNode;
import nodes.MaterialNode;
import nodes.PartialScene;
import nodes.Scene;
import nodes.ShapeNode;
import nodes.TextureNode;
import nodes.TransformNode;
import shapes.ComplexShape;
import shapes.Cylinder;
import shapes.Sphere;
import transformations.AnimatedRotation;
import transformations.Rotation;
import transformations.Scale;
import transformations.Translation;

/**
 * @author Sayf Elhawary
 */
public class ExampleProgramXML {
	/**
	 * The main program creates a window containing a panel for OpenGL drawing.
	 */

	private static Renderer renderer;

	public static void main ( String[] args ) {
		// create the window
		JFrame window = new JFrame("Midterm");

		// create the drawing panel
		// Scene
		Scene scene = new Scene(0.003f,0.193f,0.125f,1);

		// light 1
		DirectionalLight dl =
		    new DirectionalLight(Renderer.LIGHT_NUMS[0],
		                         new float[] { 0.2f, 0.2f, 0.2f, 1 },// ambient
		                         new float[] { 0.8f, 0.8f, 0.8f, 1 },// diffuse
		                         new float[] { 0.5f, 0.5f, 0.5f, 1 },// specular
		                         new float[] { 0, 5, 15 });// position
		LightNode lightNode = new LightNode(dl);
		scene.add(lightNode);

		// camera
		Camera camera = new Camera();
		camera.lookAt(0,5,30,0,0,0,0,1,0);
		camera.setScale(20);
		CameraNode cameraNode = new CameraNode(camera);
		scene.add(cameraNode);

		// planet 1
		PartialScene planet1 = new PartialScene();

		AnimatedRotation p1Rotate = new AnimatedRotation(-360,0,1,0,false);
		TransformNode rotateP1Node = new TransformNode(p1Rotate);
		planet1.addChild(rotateP1Node);

		// light 1
		PointLight pl1 = new PointLight(Renderer.LIGHT_NUMS[1],
		                                new float[] { 0.2f, 0.2f, 0.2f, 1 },// ambient
		                                new float[] { 0.8f, 0.8f, 0.4f, 1 },// diffuse
		                                new float[] { 0.5f, 0.5f, 0.5f, 1 },// specular
		                                new float[] { 8, 5, -10 });// position
		LightNode pl1Node = new LightNode(pl1);
		planet1.addChild(pl1Node);

		// sun 1
		PartialScene sun1 = new PartialScene();

		Translation translateS1 = new Translation(8,5,-10,false);
		TransformNode translateS1Node = new TransformNode(translateS1);
		sun1.addChild(translateS1Node);

		Material materialS1 =
		    new Material(new float[] { 0, 0, 0, 1 },new float[] { 0, 0, 0, 1 },
		                 new float[] { 0, 0, 0, 1 },
		                 new float[] { 0.75f, 0.75f, 0.3f, 1 },0);
		MaterialNode materialS1Node = new MaterialNode(materialS1);
		sun1.addChild(materialS1Node);

		Sphere sun = Sphere.UNIT_SPHERE;
		ShapeNode sunNode = new ShapeNode(sun);
		sun1.addChild(sunNode);

		planet1.addChild(sun1);

		scene.add(planet1);

		// planet 2

		PartialScene planet2 = new PartialScene();

		AnimatedRotation p2Rotate = new AnimatedRotation(360,0,1,0,false);
		TransformNode rotateP2Node = new TransformNode(p2Rotate);
		planet2.addChild(rotateP2Node);

		// light 2
		PointLight pl2 = new PointLight(Renderer.LIGHT_NUMS[2],
		                                new float[] { 0.2f, 0.2f, 0.2f, 1 },// ambient
		                                new float[] { 0.8f, 0.4f, 0, 1 },// diffuse
		                                new float[] { 0.5f, 0.5f, 0.5f, 1 },// specular
		                                new float[] { 10, 3, -10 });// position
		LightNode pl2Node = new LightNode(pl2);
		planet2.addChild(pl2Node);

		// sun 2
		PartialScene sun2 = new PartialScene();

		Translation translateS2 = new Translation(10,3,-10,false);
		TransformNode translateS2Node = new TransformNode(translateS2);
		sun2.addChild(translateS2Node);

		Material materialS2 =
		    new Material(new float[] { 0, 0, 0, 1 },new float[] { 0, 0, 0, 1 },
		                 new float[] { 0, 0, 0, 1 },
		                 new float[] { 1, 0.6f, 0.2f, 1 },0);
		MaterialNode materialS2Node = new MaterialNode(materialS2);
		sun2.addChild(materialS2Node);

		sun2.addChild(sunNode);

		planet2.addChild(sun2);

		scene.add(planet2);

		// death star
		PartialScene dt = new PartialScene();

		Translation translateDT = new Translation(0,-2,0,false);
		TransformNode translateDTNode = new TransformNode(translateDT);
		dt.addChild(translateDTNode);

		Tex texDT = new Tex("textures/india.gif");
		TextureNode texDTNode = new TextureNode(texDT);
		dt.addChild(texDTNode);

		Material materialDT = Material.WHITE_RUBBER;
		MaterialNode materialDTNode = new MaterialNode(materialDT);
		dt.addChild(materialDTNode);

		Scale scaleDT = new Scale(2,2,2,false);
		TransformNode scaleDTNode = new TransformNode(scaleDT);
		dt.addChild(scaleDTNode);

		 MatrixOperations m = new MatrixOperations();
		 
		 //1
//m.matrix = new float[][] { { 0, 1, 0, 0 }, { 1, 0, 0, 0 }, { 0, 0, 1, 0 },
//		                           { 0, 0, 0, 1 } };
		
//3
//	  m.setIdentity();
//	  m.matrix[2][0] = -4;

		 //2
		 //m = MatrixOperations.createShearX(1,1);
		 m = MatrixOperations.createShearY(1,1);
		// m = MatrixOperations.createShearX(1,1);
		
		 //4
//	 m.matrix = new float[][] { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0
//			 },
//	    { 0, 0, 0, 1 } };

		ComplexShape sphere = new ComplexShape(ComplexShape.HOUSE);
		// ComplexShape sphere = new
		// ComplexShape(ComplexShape.STELLATED_ICOSAHEDRON);
		 sphere.vertices = m.multiply(sphere.vertices);
		ShapeNode sphereNode = new ShapeNode(sphere);
		dt.addChild(sphereNode);

		scene.add(dt);

		renderer = new Renderer(scene);

		// add the drawing panel to the window, and finish window configuration
		window.setContentPane(renderer);
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit program when
		                                                       // window is closed
		window.setVisible(true);

		renderer.animate();

	}
}
