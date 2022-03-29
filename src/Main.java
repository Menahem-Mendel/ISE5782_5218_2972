
/**
 * name: mendel gelfand    id: 342795218   mail: mendelgel@gmail.com
 * name: josef wolf        id: 317732972   mail: yossiwolf@hotmail.com
 */
import primitives.*;
import static java.lang.System.out;
import static primitives.Util.*;

import geometries.Sphere;

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
        Sphere sp = new Sphere(
                new Point(5, 5, 5),
                3);

        Ray r = new Ray(new Point(0, 0, 0), new Vector(1, 1, 1));

        System.out.println(sp.findIntersections(r));
    }
}
