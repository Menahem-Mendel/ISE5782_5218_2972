package geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import primitives.*;

public class SphereTest {
    final double MIN = -10;
    final double MAX = 10;

    final double DELTA = 0.00001;

    double x, y, z;

    Point p;
    double r;

    Sphere sphere;

    @BeforeEach
    public void init() {
        x = Util.random(MIN, MAX);
        y = Util.random(MIN, MAX);
        z = Util.random(MIN, MAX);

        r = Util.random(MIN, MAX);

        p = new Point(x, y, z);

        sphere = new Sphere(p, r);
    }

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}
     */
    @RepeatedTest(10)
    public void getNormalTest() {
        double alpha = 2 * Math.PI * Util.random(0, 1);
        double beta = Math.acos(1 - 2 * Util.random(0, 1));

        double ax = r * Math.cos(alpha);
        double ay = r * Math.sin(alpha) * Math.cos(beta);
        double az = r * Math.sin(alpha) * Math.sin(beta);

        Point ap = new Point(ax, ay, az);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test normal vector
        assertEquals(ap.sub(p).normalize(), sphere.getNormal(ap), "getNormal() wrong normal vector");

        // =============== Boundary Values Tests ==================
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point(1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray(new Point(0.5, 0.5, 0), new Vector(3, 1, 0)));
        assertEquals(List.of(p2), result, "Ray should cross only one point");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 1, 0), new Vector(3, 1, 0))),
                "Ray shouldn't cross any point after the sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(p1, new Vector(3, 1, 0)));
        assertEquals(List.of(p2), result, "Ray starts at sphere and goes inside");

        // TC12: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(p1, new Vector(-3, -1, 0)));
        assertNull(result, "Ray starts at sphere and goes outside");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 0, 0)));
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(0, 0, 0), new Point(2, 0, 0)), result,
                "Ray starts before the sphere");

        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)));
        assertEquals(List.of(new Point(2, 0, 0)), result,
                "Ray starts at sphere and goes inside");

        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(0.5, 0, 0), new Vector(1, 0, 0)));
        assertEquals(List.of(new Point(2, 0, 0)), result,
                "Ray starts inside");

        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 0, 0)));
        assertEquals(List.of(new Point(2, 0, 0)), result,
                "Ray starts at the center");

        // TC17: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(-1, 0, 0)));
        assertNull(result, "Ray starts at sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0)));
        assertNull(result, "Ray starts after sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        result = sphere.findIntersections(new Ray(new Point(0, 1, 0), new Vector(1, 0, 0)));
        assertNull(result, "Ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        result = sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(1, 0, 0)));
        assertNull(result, "Ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        result = sphere.findIntersections(new Ray(new Point(2, 1, 0), new Vector(1, 0, 0)));
        assertNull(result, "Ray starts after the tangent point");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's
        // center line
    
        result = sphere.findIntersections(new Ray(new Point(0, 2, 0), new Vector(1, 0, 0)));
        assertNull(result, "Ray starts before the tangent point");

    }

}
