package main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import lights.*;
import nodes.*;
import shapes.*;
import transformations.*;

/**
 * @author Sayf Elhawary
 */
public class FileManager {

	/**
	 * Loads a scene from an XML file. This method parses the XML file and
	 * constructs a scene graph based on its contents. The method traverses
	 * through the XML nodes, creating corresponding objects in the scene.
	 * 
	 * @param fileName
	 *          The path to the XML file.
	 * @return A Scene object constructed from the XML file.
	 */
	public static Scene loadXMLScene ( String fileName ) {

		Scene scene = new Scene(0,0,0,1);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			File xmlFile = new File(fileName);
			Document doc = dBuilder.parse(xmlFile);
			PartialScene currentNode = scene.getRootNode();

			NodeList children = doc.getDocumentElement().getChildNodes();
			for ( int i = 0 ; i < children.getLength() ; i++ ) {
				traverseNodes(children.item(i),currentNode);
			}

		} catch ( ParserConfigurationException | SAXException | IOException e ) {
			e.printStackTrace();
		}
		return scene;
	}

	/**
	 * Traverses through the nodes in the XML document and constructs the scene
	 * graph.
	 * 
	 * @param node
	 *          The current XML node being processed.
	 * @param parent
	 *          The parent node in the scene graph.
	 */
	private static void traverseNodes ( Node node, PartialScene parent ) {
		PartialScene currentNode = createSceneGraph(node,parent);

		NodeList children = node.getChildNodes();
		for ( int i = 0 ; i < children.getLength() ; i++ ) {
			traverseNodes(children.item(i),currentNode);
		}
	}

	/**
	 * Creates a scene graph based on the provided XML node. This method
	 * identifies the type of XML node (e.g., light, shape) and creates the
	 * corresponding object in the scene graph.
	 * 
	 * @param xmlNode
	 *          The XML node to be processed.
	 * @param parent
	 *          The parent node in the scene graph.
	 * @return The newly created node in the scene graph.
	 */
	private static PartialScene createSceneGraph ( Node xmlNode,
	                                               PartialScene parent ) {
		String nodeName = xmlNode.getNodeName();
		System.out.println(nodeName);
		PartialScene currentNode = null;

		if ( nodeName.equals("PointLight") ) {
			int currValue = Integer.parseInt(xmlNode.getAttributes()
			    .getNamedItem("number").getNodeValue());
			String[] currAttribute = xmlNode.getAttributes().getNamedItem("ambient")
			    .getNodeValue().split(",");
			float[] ambient = new float[] { Float.parseFloat(currAttribute[0]),
			                                Float.parseFloat(currAttribute[1]),
			                                Float.parseFloat(currAttribute[2]),
			                                Float.parseFloat(currAttribute[4]) };
			currAttribute = xmlNode.getAttributes().getNamedItem("diffuse")
			    .getNodeValue().split(",");
			float[] diffuse = new float[] { Float.parseFloat(currAttribute[0]),
			                                Float.parseFloat(currAttribute[1]),
			                                Float.parseFloat(currAttribute[2]),
			                                Float.parseFloat(currAttribute[4]) };
			currAttribute = xmlNode.getAttributes().getNamedItem("specular")
			    .getNodeValue().split(",");
			float[] specular = new float[] { Float.parseFloat(currAttribute[0]),
			                                 Float.parseFloat(currAttribute[1]),
			                                 Float.parseFloat(currAttribute[2]),
			                                 Float.parseFloat(currAttribute[4]) };
			currAttribute = xmlNode.getAttributes().getNamedItem("position")
			    .getNodeValue().split(",");
			float[] position = new float[] { Float.parseFloat(currAttribute[0]),
			                                 Float.parseFloat(currAttribute[1]),
			                                 Float.parseFloat(currAttribute[2]),
			                                 Float.parseFloat(currAttribute[4]) };

			PointLight pl = new PointLight(currValue,ambient,// ambient
			                               diffuse,// diffuse
			                               specular,// specular
			                               position);// position
			LightNode lightNode = new LightNode(pl);
			currentNode = lightNode;
			parent.addChild(lightNode);
		} else if ( nodeName.equals("DirectionalLight") ) {
			int currValue = Integer.parseInt(xmlNode.getAttributes()
			    .getNamedItem("number").getNodeValue());
			String[] currAttribute = xmlNode.getAttributes().getNamedItem("ambient")
			    .getNodeValue().split(",");
			float[] ambient = new float[] { Float.parseFloat(currAttribute[0]),
			                                Float.parseFloat(currAttribute[1]),
			                                Float.parseFloat(currAttribute[2]),
			                                Float.parseFloat(currAttribute[4]) };
			currAttribute = xmlNode.getAttributes().getNamedItem("diffuse")
			    .getNodeValue().split(",");
			float[] diffuse = new float[] { Float.parseFloat(currAttribute[0]),
			                                Float.parseFloat(currAttribute[1]),
			                                Float.parseFloat(currAttribute[2]),
			                                Float.parseFloat(currAttribute[4]) };
			currAttribute = xmlNode.getAttributes().getNamedItem("specular")
			    .getNodeValue().split(",");
			float[] specular = new float[] { Float.parseFloat(currAttribute[0]),
			                                 Float.parseFloat(currAttribute[1]),
			                                 Float.parseFloat(currAttribute[2]),
			                                 Float.parseFloat(currAttribute[4]) };
			currAttribute = xmlNode.getAttributes().getNamedItem("position")
			    .getNodeValue().split(",");
			float[] position = new float[] { Float.parseFloat(currAttribute[0]),
			                                 Float.parseFloat(currAttribute[1]),
			                                 Float.parseFloat(currAttribute[2]),
			                                 Float.parseFloat(currAttribute[4]) };

			DirectionalLight dl = new DirectionalLight(currValue,ambient,// ambient
			                                           diffuse,// diffuse
			                                           specular,// specular
			                                           position);// position
			LightNode lightNode = new LightNode(dl);
			currentNode = lightNode;
			parent.addChild(lightNode);
		} else if ( nodeName.equals("SpotLight") ) {
			int currValue =
			    Integer.parseInt(xmlNode.getAttributes().item(0).getNodeValue());
			String[] currAttribute =
			    xmlNode.getAttributes().item(1).getNodeValue().split(",");
			float[] ambient = new float[] { Float.parseFloat(currAttribute[0]),
			                                Float.parseFloat(currAttribute[1]),
			                                Float.parseFloat(currAttribute[2]),
			                                Float.parseFloat(currAttribute[4]) };
			currAttribute = xmlNode.getAttributes().item(2).getNodeValue().split(",");
			float[] diffuse = new float[] { Float.parseFloat(currAttribute[0]),
			                                Float.parseFloat(currAttribute[1]),
			                                Float.parseFloat(currAttribute[2]),
			                                Float.parseFloat(currAttribute[4]) };
			currAttribute = xmlNode.getAttributes().item(3).getNodeValue().split(",");
			float[] specular = new float[] { Float.parseFloat(currAttribute[0]),
			                                 Float.parseFloat(currAttribute[1]),
			                                 Float.parseFloat(currAttribute[2]),
			                                 Float.parseFloat(currAttribute[4]) };
			currAttribute = xmlNode.getAttributes().item(4).getNodeValue().split(",");
			float[] position = new float[] { Float.parseFloat(currAttribute[0]),
			                                 Float.parseFloat(currAttribute[1]),
			                                 Float.parseFloat(currAttribute[2]),
			                                 Float.parseFloat(currAttribute[4]) };
			currAttribute = xmlNode.getAttributes().item(5).getNodeValue().split(",");
			float[] direction = new float[] { Float.parseFloat(currAttribute[0]),
			                                  Float.parseFloat(currAttribute[1]),
			                                  Float.parseFloat(currAttribute[2]),
			                                  Float.parseFloat(currAttribute[4]) };
			int cutoff =
			    Integer.parseInt(xmlNode.getAttributes().item(6).getNodeValue());
			int exponent =
			    Integer.parseInt(xmlNode.getAttributes().item(7).getNodeValue());

			SpotLight sl = new SpotLight(currValue,ambient,// ambient
			                             diffuse,// diffuse
			                             specular,// specular
			                             position,direction,cutoff,exponent);// position
			LightNode lightNode = new LightNode(sl);
			currentNode = lightNode;
			parent.addChild(lightNode);
		} else if ( nodeName.equals("Torus") ) {
			Torus shape = new Torus(xmlNode.getAttributes().item(0).getNodeValue(),
			                        Double.parseDouble(xmlNode.getAttributes().item(1)
			                            .getNodeValue()),
			                        Double.parseDouble(xmlNode.getAttributes().item(2)
			                            .getNodeValue()),
			                        Integer.parseInt(xmlNode.getAttributes().item(3)
			                            .getNodeValue()),
			                        Integer.parseInt(xmlNode.getAttributes().item(4)
			                            .getNodeValue()));
			ShapeNode shapeNode = new ShapeNode(shape);
			currentNode = shapeNode;
			parent.addChild(shapeNode);
		} else if ( nodeName.equals("Cylinder") ) {
			Cylinder shape =
			    new Cylinder(xmlNode.getAttributes().item(0).getNodeValue(),
			                 Double.parseDouble(xmlNode.getAttributes().item(1)
			                     .getNodeValue()),
			                 Double.parseDouble(xmlNode.getAttributes().item(2)
			                     .getNodeValue()),
			                 Integer.parseInt(xmlNode.getAttributes().item(3)
			                     .getNodeValue()),
			                 Integer.parseInt(xmlNode.getAttributes().item(4)
			                     .getNodeValue()));
			ShapeNode shapeNode = new ShapeNode(shape);
			currentNode = shapeNode;
			parent.addChild(shapeNode);

		} else if ( nodeName.equals("Teapot") ) {
			Teapot shape =
			    new Teapot(xmlNode.getAttributes().item(0).getNodeValue(),Float
			        .parseFloat(xmlNode.getAttributes().item(1).getNodeValue()));
			ShapeNode shapeNode = new ShapeNode(shape);
			currentNode = shapeNode;
			parent.addChild(shapeNode);

		} else if ( nodeName.equals("Sphere") ) {

			if ( xmlNode.getAttributes().item(0).getNodeValue().toUpperCase()
			    .equals("UNIT_SPHERE") ) {
				Sphere shape = Sphere.UNIT_SPHERE;
				ShapeNode shapeNode = new ShapeNode(shape);
				currentNode = shapeNode;
				parent.addChild(shapeNode);
			} else {

				Sphere shape =
				    new Sphere(xmlNode.getAttributes().item(0).getNodeValue(),
				               Double.parseDouble(xmlNode.getAttributes().item(1)
				                   .getNodeValue()),
				               Integer.parseInt(xmlNode.getAttributes().item(2)
				                   .getNodeValue()),
				               Integer.parseInt(xmlNode.getAttributes().item(3)
				                   .getNodeValue()));
				ShapeNode shapeNode = new ShapeNode(shape);
				currentNode = shapeNode;
				parent.addChild(shapeNode);
			}

		} else if ( nodeName.equals("ComplexShape") ) {

			// don't know how to do it

		} else if ( nodeName.equals("Cone") ) {

			Cone shape = new Cone(xmlNode.getAttributes().item(0).getNodeValue(),
			                      Double.parseDouble(xmlNode.getAttributes().item(1)
			                          .getNodeValue()),
			                      Double.parseDouble(xmlNode.getAttributes().item(2)
			                          .getNodeValue()),
			                      Integer.parseInt(xmlNode.getAttributes().item(3)
			                          .getNodeValue()),
			                      Integer.parseInt(xmlNode.getAttributes().item(4)
			                          .getNodeValue()));
			ShapeNode shapeNode = new ShapeNode(shape);
			currentNode = shapeNode;
			parent.addChild(shapeNode);

		} else if ( nodeName.equals("Cube") ) {
			Cube shape = new Cube(xmlNode.getAttributes().item(0).getNodeValue(),Float
			    .parseFloat(xmlNode.getAttributes().item(1).getNodeValue()));
			ShapeNode shapeNode = new ShapeNode(shape);
			currentNode = shapeNode;
			parent.addChild(shapeNode);

		} else if ( nodeName.equals("Rotation") ) {

			boolean tex =
			    xmlNode.getAttributes().item(4).getNodeValue().equals("true") ? true
			        : false;

			Rotation transform = new Rotation(
			                                  Float.parseFloat(xmlNode.getAttributes()
			                                      .item(0).getNodeValue()),
			                                  Float.parseFloat(xmlNode.getAttributes()
			                                      .item(1).getNodeValue()),
			                                  Float.parseFloat(xmlNode.getAttributes()
			                                      .item(2).getNodeValue()),
			                                  Float.parseFloat(xmlNode.getAttributes()
			                                      .item(3).getNodeValue()),
			                                  tex);
			TransformNode tNode = new TransformNode(transform);
			currentNode = tNode;
			parent.addChild(tNode);

		} else if ( nodeName.equals("Scale") ) {

			boolean tex =
			    xmlNode.getAttributes().item(3).getNodeValue().equals("true") ? true
			        : false;

			Scale transform = new Scale(
			                            Float.parseFloat(xmlNode.getAttributes()
			                                .item(0).getNodeValue()),
			                            Float.parseFloat(xmlNode.getAttributes()
			                                .item(1).getNodeValue()),
			                            Float.parseFloat(xmlNode.getAttributes()
			                                .item(2).getNodeValue()),
			                            tex);
			TransformNode tNode = new TransformNode(transform);
			currentNode = tNode;
			parent.addChild(tNode);

		} else if ( nodeName.equals("Translation") ) {

			boolean tex =
			    xmlNode.getAttributes().item(3).getNodeValue().equals("true") ? true
			        : false;

			AnimatedTranslation transform =
			    new AnimatedTranslation(Float
			        .parseFloat(xmlNode.getAttributes().item(0).getNodeValue()),
			                            Float.parseFloat(xmlNode.getAttributes()
			                                .item(1).getNodeValue()),
			                            Float.parseFloat(xmlNode.getAttributes()
			                                .item(2).getNodeValue()),
			                            tex);
			TransformNode tNode = new TransformNode(transform);
			currentNode = tNode;
			parent.addChild(tNode);

		} else if ( nodeName.equals("AnimatedRotation") ) {

			boolean tex =
			    xmlNode.getAttributes().item(4).getNodeValue().equals("true") ? true
			        : false;

			AnimatedRotation transform =
			    new AnimatedRotation(Float
			        .parseFloat(xmlNode.getAttributes().item(0).getNodeValue()),
			                         Float.parseFloat(xmlNode.getAttributes().item(1)
			                             .getNodeValue()),
			                         Float.parseFloat(xmlNode.getAttributes().item(2)
			                             .getNodeValue()),
			                         Float.parseFloat(xmlNode.getAttributes().item(3)
			                             .getNodeValue()),
			                         tex);
			TransformNode tNode = new TransformNode(transform);
			currentNode = tNode;
			parent.addChild(tNode);

		} else if ( nodeName.equals("AnimatedScale") ) {

			boolean tex =
			    xmlNode.getAttributes().item(3).getNodeValue().equals("true") ? true
			        : false;

			AnimatedScale transform =
			    new AnimatedScale(Float
			        .parseFloat(xmlNode.getAttributes().item(0).getNodeValue()),
			                      Float.parseFloat(xmlNode.getAttributes().item(1)
			                          .getNodeValue()),
			                      Float.parseFloat(xmlNode.getAttributes().item(2)
			                          .getNodeValue()),
			                      tex);
			TransformNode tNode = new TransformNode(transform);
			currentNode = tNode;
			parent.addChild(tNode);

		} else if ( nodeName.equals("AnimatedTranslation") ) {
			boolean tex =
			    xmlNode.getAttributes().item(3).getNodeValue().equals("true") ? true
			        : false;

			AnimatedTranslation transform =
			    new AnimatedTranslation(Float
			        .parseFloat(xmlNode.getAttributes().item(0).getNodeValue()),
			                            Float.parseFloat(xmlNode.getAttributes()
			                                .item(1).getNodeValue()),
			                            Float.parseFloat(xmlNode.getAttributes()
			                                .item(2).getNodeValue()),
			                            tex);
			TransformNode tNode = new TransformNode(transform);
			currentNode = tNode;
			parent.addChild(tNode);

		} else if ( nodeName.equals("Camera") ) {

			String[] currAttribute =
			    xmlNode.getAttributes().item(0).getNodeValue().split(",");
			float[] lookAt = new float[] { Float.parseFloat(currAttribute[0]),
			                               Float.parseFloat(currAttribute[1]),
			                               Float.parseFloat(currAttribute[2]),
			                               Float.parseFloat(currAttribute[4]),
			                               Float.parseFloat(currAttribute[2]),
			                               Float.parseFloat(currAttribute[4]) };

			Camera c = new Camera();
			c.lookAt(lookAt[0],lookAt[1],lookAt[2],lookAt[3],lookAt[4],lookAt[5],
			         lookAt[6],lookAt[7],lookAt[8]);
			c.setScale(Float
			    .parseFloat(xmlNode.getAttributes().item(1).getNodeValue()));
			CameraNode materialNode = new CameraNode(c);
			currentNode = materialNode;
			parent.addChild(materialNode);

		} else if ( nodeName.equals("Material") ) {
			if ( xmlNode.getAttributes().item(0).getNodeValue().toUpperCase()
			    .equals("OBSIDEAN") ) {
				Material m = Material.OBSIDIAN;
				MaterialNode materialNode = new MaterialNode(m);
				currentNode = materialNode;
				parent.addChild(materialNode);
			} else {
				float currValue =
				    Float.parseFloat(xmlNode.getAttributes().item(4).getNodeValue());
				String[] currAttribute =
				    xmlNode.getAttributes().item(0).getNodeValue().split(",");
				float[] ambient = new float[] { Float.parseFloat(currAttribute[0]),
				                                Float.parseFloat(currAttribute[1]),
				                                Float.parseFloat(currAttribute[2]),
				                                Float.parseFloat(currAttribute[4]) };
				currAttribute =
				    xmlNode.getAttributes().item(1).getNodeValue().split(",");
				float[] diffuse = new float[] { Float.parseFloat(currAttribute[0]),
				                                Float.parseFloat(currAttribute[1]),
				                                Float.parseFloat(currAttribute[2]),
				                                Float.parseFloat(currAttribute[4]) };
				currAttribute =
				    xmlNode.getAttributes().item(2).getNodeValue().split(",");
				float[] specular = new float[] { Float.parseFloat(currAttribute[0]),
				                                 Float.parseFloat(currAttribute[1]),
				                                 Float.parseFloat(currAttribute[2]),
				                                 Float.parseFloat(currAttribute[4]) };
				currAttribute =
				    xmlNode.getAttributes().item(3).getNodeValue().split(",");
				float[] emission = new float[] { Float.parseFloat(currAttribute[0]),
				                                 Float.parseFloat(currAttribute[1]),
				                                 Float.parseFloat(currAttribute[2]),
				                                 Float.parseFloat(currAttribute[4]) };

				Material m = new Material(ambient,// ambient
				                          diffuse,// diffuse
				                          specular,// specular
				                          emission,currValue);// position
				MaterialNode materialNode = new MaterialNode(m);
				currentNode = materialNode;
				parent.addChild(materialNode);
			}
		} else if ( nodeName.equals("Texture") ) {

			Tex tex = new Tex(xmlNode.getAttributes().item(0).getNodeValue());
			TextureNode texNode = new TextureNode(tex);
			currentNode = texNode;
			parent.addChild(texNode);

		} else {
			currentNode = new PartialScene();
		}

		return currentNode;
	}
}
