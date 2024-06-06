# Scene Graph

## Introduction
Scene Graph is a Java project that enhances the JOGL library, enabling advanced 3D scene management and rendering. Designed for developers, this framework facilitates the creation and manipulation of complex 3D scenes with dynamic lighting, texturing, and scene transformations.

## Features
- **Extended JOGL Library Capabilities**: Augments JOGL with tools to manage complex 3D rendering.
- **Node-Based Scene Management**: Utilizes nodes such as `CameraNode`, `LightNode`, `MaterialNode`, `ShapeNode`, and others for flexible scene management.
- **Advanced Lighting Models**: Supports different lighting types like `DirectionalLight`, `PointLight`, and `SpotLight` for realistic lighting effects.
- **Material and Texture Handling**: Manages surfaces and textures through classes like `MaterialNode` and `TextureNode`.
- **Support for Geometric Shapes**: Includes a variety of predefined geometric shapes such as cubes, spheres, cones, cylinders, and more, which are easy to integrate into any scene.
- **Dynamic Animations and Transformations**: Offers classes like `AnimatedRotation`, `AnimatedScale`, and `AnimatedTranslation` for dynamic object movements and transformations.
- **XML Scene Configuration**: Simplifies scene setup using XML for straightforward configuration and maintenance.
- **Transformation Utilities**: `TransformNode` class provides capabilities for dynamic object transformations.

## Installation
Follow these steps to set up Scene Graph:
1. Ensure you have Java (version 8 or higher) and JOGL installed.
2. Clone the repository:
   """
   git clone https://github.com/SMFEH323/SceneGraph.git
   """
3. Import the project into your IDE of choice that supports Java (e.g., IntelliJ IDEA, Eclipse).
4. Set up the JOGL library paths as per your system configuration.

## Usage
Here is a basic example of initializing and using Scene Graph in a Java application:

\```java
// Main class to demonstrate basic usage of Scene Graph
public class ExampleProgram {
    public static void main(String[] args) {
        Scene scene = new Scene();
        scene.addNode(new CameraNode());
        scene.addNode(new LightNode());
        // Additional scene setup and rendering
    }
}
\```

For XML-based scene configuration, see `ExampleProgramXML.java`.

## Contributing
Contributions are welcome! If you'd like to contribute, please fork the repository and use a feature branch. Pull requests are warmly welcome.

## Contact
For any inquiries, please reach out via email at elhawaryseif@gmail.com.
