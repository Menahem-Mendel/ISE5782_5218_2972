
/** 
 * @formatter:off
 * name: mendel gelfand id: 342795218 mail: mendelgel@gmail.com
 * name: josef wolf id: 317732972 mail: yossiwolf@hotmail.com
 * @formatter:on
 */
<<<<<<< HEAD
import primitives.*;
import renderer.*;
import scene.*;
import lighting.*;
=======
>>>>>>> d6418e84447d449acb6a3a2f9d06136516695b14

public final class Main {

<<<<<<< HEAD
        private static final java.awt.Color BLUE = null;

        /**
         * Main program to tests initial functionality of the 1st stage
         * 
         * @param args irrelevant here
         */
        public static void main(String[] args) {
                 Scene scene1 = new Scene("Test scene");
                 Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                                .setVPSize(150, 150) //
                                .setVPDistance(1000);
                Color trCL = new Color(800, 500, 250); // Triangles test Color of Light
                 Color spCL = new Color(800, 500, 0); // Sphere test Color of Light

                 Geometry sphere = new Sphere(new Point(0, 0, -50), 50d) //
			.setEmission(new Color(BLUE).reduce(2)) //
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

                scene1.geometries.add(sphere);
                scene1.lights.add(new DirectionalLight(spCL, new Vector(1, 1, -0.5)));

                ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 500, 500);
                camera1.setImageWriter(imageWriter) //
                                .setRayTracer(new RayTracerBasic(scene1)) //
                                .renderImage() //
                                .writeToImage(); //
        }
=======
>>>>>>> d6418e84447d449acb6a3a2f9d06136516695b14
}
