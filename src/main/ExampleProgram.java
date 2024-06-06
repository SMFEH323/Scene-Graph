package main;

import javax.swing.JFrame;

import lights.DirectionalLight;
import lights.PointLight;
import nodes.*;
import shapes.Cylinder;
import shapes.Sphere;
import transformations.*;

/**
 * @author Sayf Elhawary
 */
public class ExampleProgram {

	/**
	 * The main program creates a window containing a panel for OpenGL drawing.
	 */

	private static Renderer renderer;

	public static void main ( String[] args ) {
		// create the window
		JFrame window = new JFrame("Midterm");

		// Scene
		Scene scene = new Scene(0,0,0,1);

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

		Material materialDT = Material.OBSIDIAN;
		MaterialNode materialDTNode = new MaterialNode(materialDT);
		dt.addChild(materialDTNode);

		Scale scaleDT = new Scale(2,2,2,false);
		TransformNode scaleDTNode = new TransformNode(scaleDT);
		dt.addChild(scaleDTNode);

		Sphere sphere = new Sphere("texture",1,32,64);
		ShapeNode sphereNode = new ShapeNode(sphere);
		dt.addChild(sphereNode);

		scene.add(dt);

		AnimatedRotation arotate1C1 = new AnimatedRotation(360,0,1,0,false);
		TransformNode arotate1Node1 = new TransformNode(arotate1C1);
		scene.add(arotate1Node1);

		// lightsaber
		PartialScene lightsaber1 = new PartialScene();

		Translation translate2C1 = new Translation(-5,-1.5f,0,false);
		TransformNode translate2Node1 = new TransformNode(translate2C1);
		lightsaber1.addChild(translate2Node1);

		Rotation rotate2C1 = new Rotation(45,0,0,1,false);
		TransformNode rotate2Node1 = new TransformNode(rotate2C1);
		lightsaber1.addChild(rotate2Node1);

		Rotation rotate1C1 = new Rotation(90,0,1,0,false);
		TransformNode rotate1Node1 = new TransformNode(rotate1C1);
		lightsaber1.addChild(rotate1Node1);

		// hilt
		PartialScene hilt1 = new PartialScene();

		Tex tex = new Tex("textures/blkrose.jpg");
		TextureNode texNode = new TextureNode(tex);
		hilt1.addChild(texNode);

		Material material1 = Material.GOLD;
		MaterialNode material1Node = new MaterialNode(material1);
		hilt1.addChild(material1Node);

		// cylinder 1
		PartialScene cylinderFirst = new PartialScene();

		Translation translate1C1 = new Translation(0,0,-2.5f,false);
		TransformNode translate1Node1 = new TransformNode(translate1C1);
		cylinderFirst.addChild(translate1Node1);

		Cylinder cylinder1 = new Cylinder("texture",0.6f,5,32,64);
		ShapeNode cNode1 = new ShapeNode(cylinder1);
		cylinderFirst.addChild(cNode1);

		hilt1.addChild(cylinderFirst);

		// cylinder 2
		Material material2 = Material.PEARL;
		MaterialNode material2Node = new MaterialNode(material2);
		hilt1.addChild(material2Node);

		PartialScene cylinderSecond = new PartialScene();

		Translation translate1C2 = new Translation(0,0,-2,false);
		TransformNode translate1Node2 = new TransformNode(translate1C2);
		cylinderSecond.addChild(translate1Node2);

		Cylinder cylinder2 = new Cylinder("texture",0.75f,0.25f,32,64);
		ShapeNode cNode2 = new ShapeNode(cylinder2);
		cylinderSecond.addChild(cNode2);

		hilt1.addChild(cylinderSecond);

		// cylinder 3
		PartialScene cylinderThird = new PartialScene();

		Translation translate1C3 = new Translation(0,0,1.5f,false);
		TransformNode translate1Node3 = new TransformNode(translate1C3);
		cylinderThird.addChild(translate1Node3);

		cylinderThird.addChild(cNode2);

		hilt1.addChild(cylinderThird);

		// cylinder 4
		PartialScene cylinderFourth = new PartialScene();

		Translation translate1C4 = new Translation(0,0,2,false);
		TransformNode translate1Node4 = new TransformNode(translate1C4);
		cylinderFourth.addChild(translate1Node4);

		cylinderFourth.addChild(cNode2);

		hilt1.addChild(cylinderFourth);

		lightsaber1.addChild(hilt1);

		// light from saber

		PartialScene saber1 = new PartialScene();

		Material material3 = new Material(new float[] { 0, 0, 0, 1 },// ambient
		                                  new float[] { 0, 0, 0, 1 },// diffuse
		                                  new float[] { 0, 0, 0, 1 },// specular
		                                  new float[] { 1, 0, 0, 1 }, // emission
		                                  (float) (0)); // shininess
		MaterialNode material3Node = new MaterialNode(material3);
		saber1.addChild(material3Node);

		// cylinder 5

		PartialScene cylinderFifth = new PartialScene();

		Translation translate1C5 = new Translation(0,0,2.5f,false);
		TransformNode translate1Node5 = new TransformNode(translate1C5);
		cylinderFifth.addChild(translate1Node5);

		Cylinder cylinder5 = new Cylinder("solid",0.5f,12,32,64);
		ShapeNode cNode5 = new ShapeNode(cylinder5);
		cylinderFifth.addChild(cNode5);

		saber1.addChild(cylinderFifth);

		lightsaber1.addChild(saber1);

		scene.add(lightsaber1);

		// lightaber 2
		PartialScene lightsaber2 = new PartialScene();

		Translation translate2C2 = new Translation(5,-1.5f,1,false);
		TransformNode translate2Node2 = new TransformNode(translate2C2);
		lightsaber2.addChild(translate2Node2);

		Rotation rotate2C2 = new Rotation(-45,0,0,1,false);
		TransformNode rotate2Node2 = new TransformNode(rotate2C2);
		lightsaber2.addChild(rotate2Node2);

		Rotation rotate1C2 = new Rotation(-90,0,1,0,false);
		TransformNode rotate1Node2 = new TransformNode(rotate1C2);
		lightsaber2.addChild(rotate1Node2);

		// hilt
		PartialScene hilt2 = new PartialScene();

		hilt2.addChild(texNode);

		hilt2.addChild(material2Node);

		// cylinder 1

		hilt2.addChild(cylinderFirst);

		// cylinder 2
		hilt2.addChild(material1Node);

		hilt2.addChild(cylinderSecond);

		// cylinder 3

		hilt2.addChild(cylinderThird);

		// cylinder 4
		hilt2.addChild(cylinderFourth);

		lightsaber2.addChild(hilt2);

		// light from saber

		PartialScene saber2 = new PartialScene();

		Material material4 = new Material(new float[] { 0, 0, 0, 1 },// ambient
		                                  new float[] { 0, 0, 0, 1 },// diffuse
		                                  new float[] { 0, 0, 0, 1 },// specular
		                                  new float[] { 0, 1, 0, 1 }, // emission
		                                  (float) (0)); // shininess
		MaterialNode material4Node = new MaterialNode(material4);
		saber2.addChild(material4Node);

		// cylinder 5

		saber2.addChild(cylinderFifth);

		lightsaber2.addChild(saber2);

		scene.add(lightsaber2);

		// create the drawing panel
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
