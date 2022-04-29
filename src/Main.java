
/**
 * name: mendel gelfand    id: 342795218   mail: mendelgel@gmail.com
 * name: josef wolf        id: 317732972   mail: yossiwolf@hotmail.com
 */
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;
import primitives.Color;

import static java.awt.Color.*;

import geometries.*;
import lighting.AmbientLight;

/**
 * Test program for the 1st stage
 *
 * @author Dan Zilberstein
 */
public final class Main {

    /**
     * Main program to tests initial functionality of the 1st stage
     * 
     * @param args irrelevant here
     */
    public static void main(String[] args) {
        Scene scene = new Scene("Test scene")//
        .setAmbientLight(new AmbientLight(new Color(255, 191, 191), new Point(1, 1, 1).getXYZ()))
        .setBackground(new Color(75, 127, 90));

scene.geometries.add(new Sphere(new Point(0, 0, -100), 50),
        new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)),
        new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)),
        new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100)));

Camera camera = new Camera(new Point(0,0,0), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
        .setVPDistance(100)
        .setVPSize(500, 500)
        .setImageWriter(new ImageWriter("base render test2", 1000, 1000))
        .setRayTracer(new RayTracerBasic(scene));

camera.renderImage();
camera.printGrid(100, new Color(java.awt.Color.YELLOW));
camera.writeToImage();
    }
}
