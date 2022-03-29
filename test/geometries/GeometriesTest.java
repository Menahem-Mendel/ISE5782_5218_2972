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

        // Polygon pl = new Polygon(
        // new Point(2, 0, 0),
        // new Point(2, 0, 2),
        // new Point(0, 2, 0),
        // new Point(0, 2, 2));

        Sphere sp = new Sphere(
                new Point(5, 5, 5),
                3);

        Plane pln = new Plane(
                new Point(-1, 0, 0),
                new Point(0, -1, 0),
                new Point(0, 0, -1));

        // Cylinder cl = new Cylinder(
        // new Ray(
        // new Point(-5, -5, -5),
        // new Vector(-1, -1, -1)),
        // 3, 3);

        Geometries[] gg = {
                new Geometries(
                        tr,
                        pln,
                        sp),

                new Geometries(),

                new Geometries(
                        tr,
                        pln,
                        sp),

                new Geometries(
                        tr,
                        pln,
                        sp),

                new Geometries(
                        tr,
                        pln,
                        sp),
        };

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line goes through few geometries but not all of them
        Ray r = new Ray(new Point(0, 0, 0), new Vector(1, 1, 1));
        List<Point> result = gg[0].findIntersections(r);
        int expected = sp.findIntersections(r).size() + tr.findIntersections(r).size();
        assertEquals(expected, result.size());
        // =============== Boundary Values Tests ==================

        // TC11: empty collection of geometries
        result = gg[1].findIntersections(r);
        assertNull(result, "empty collection of geometries");

        // TC12: Ray's line does not go through any geometry in the collection
        r = new Ray(new Point(0, 0, 0), new Vector(-1, 1, 1));
        result = gg[2].findIntersections(r);
        assertNull(result, "Ray's line does not go through any geometry in the collection");

        // TC13: Ray's line goes through only one geometry element in the collection

        r = new Ray(new Point(0, 0, 0), new Vector(-1, -1, -1));
        result = gg[3].findIntersections(r);
        expected = pln.findIntersections(r).size();
        assertEquals(expected, result.size());

        // TC14: Ray's line goes through all elements in the collection
        r = new Ray(new Point(-2, -2, -2), new Vector(1, 1, 1));
        result = gg[4].findIntersections(r);
        expected = sp.findIntersections(r).size() + tr.findIntersections(r).size() + pln.findIntersections(r).size();
        assertEquals(expected, result.size());
    }
}
