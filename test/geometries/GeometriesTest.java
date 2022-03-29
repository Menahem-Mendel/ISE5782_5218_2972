package geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import primitives.*;

public class GeometriesTest {

    @Test
    public void findIntersectionsTest() {
        Triangle tr = new Triangle(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1));

        Polygon pl = new Polygon(
                new Point(2, 0, 0),
                new Point(2, 0, 2),
                new Point(0, 2, 0),
                new Point(0, 2, 2));

        Cylinder cl = new Cylinder(
                new Ray(
                        new Point(-5, -5, -5),
                        new Vector(-1, -1, -1)),
                3, 3);

        Geometries[] gg = {
                new Geometries(
                        tr,
                        pl,
                        cl),

                new Geometries(),
                new Geometries(),
                new Geometries(),
                new Geometries(),
        };

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line goes through few geometries but not all of them
        Ray r = new Ray(new Point(0, 0, 0), new Vector(1, 1, 1));
        List<Point> result = gg[0].findIntersections(r);
        int expected = cl.findIntersections(r).size() + tr.findIntersections(r).size() + pl.findIntersections(r).size();
        assertEquals(expected, result.size());
        // =============== Boundary Values Tests ==================

        // TC11: empty collection of geometries
        gg[1].findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1)));

        // TC12: Ray's line does not go through any geometry in the collection
        gg[2].findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1)));

        // TC13: Ray's line goes through only one geometry element in the collection
        gg[3].findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1)));

        // TC14: Ray's line goes through all elements in the collection
        gg[4].findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1)));
    }
}
