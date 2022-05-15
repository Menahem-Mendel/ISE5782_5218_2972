package geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import primitives.*;

public class TriangleTest {
	final double MIN = -10;
	final double MAX = 10;

	final double DELTA = 0.00001;

	Point[] pp = new Point[3];

	Triangle triangle;

	@BeforeEach
	public void init() {
		for (int i = 0; i < 3; ++i) {
			double x = Util.random(MIN, MAX);
			double y = Util.random(MIN, MAX);
			double z = Util.random(MIN, MAX);

			pp[i] = new Point(x, y, z);
		}

		triangle = new Triangle(pp[0], pp[1], pp[2]);
	}

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
	 */
	@RepeatedTest(10)
	public void getNormalTest() {
		Vector lhs = pp[1].sub(pp[0]);
		Vector rhs = pp[2].sub(pp[0]);
		Vector vcp = lhs.cross(rhs).normalize();
		Random r = new Random();

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test normal vector
		assertEquals(vcp, triangle.getNormal(pp[r.nextInt(3)]), "getNormal() wrong normal vector");
	}

	/**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Triangle tr = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
		Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
		Ray ray;

		// ============ Equivalence Partitions Tests ==============
		// TC01: intersection inside the triangle
		ray = new Ray(new Point(1, 1, 1), new Vector(-1, -1, -1));

		assertEquals(List.of(new Point(1d / 3, 1d / 3, 1d / 3)), tr.findIntersections(ray),
				"intersection inside the triangle");

		// TC02: infront of one of the vertices
		ray = new Ray(new Point(0, 0, 2), new Vector(-1, -1, 0));

		assertEquals(List.of(new Point(-0.5, -0.5, 2)), pl.findIntersections(ray),
				"infront of one of the vertices");
		assertNull(tr.findIntersections(ray), "infront of one of the vertices");

		// TC03: infront of one of the edges
		ray = new Ray(new Point(0, 0, -1), new Vector(1, 1, 0));

		assertEquals(List.of(new Point(1, 1, -1)), pl.findIntersections(ray),
				"infront of one of the edges");
		assertNull(tr.findIntersections(ray), "infront of one of the edges");

		// =============== Boundary Values Tests ==================
		// TC11: on one of the vertices
		ray = new Ray(Point.ZERO, new Vector(1, 0, 0));

		assertEquals(List.of(new Point(1, 0, 0)), pl.findIntersections(ray),
				"on the continuation of one of the vertices");
		assertNull(tr.findIntersections(ray), "on the continuation of one of the vertices");

		// TC12: on one of the edges
		ray = new Ray(Point.ZERO, new Vector(0.5, 0.5, 0));

		assertEquals(List.of(new Point(0.5, 0.5, 0)), pl.findIntersections(ray),
				"infront of one of the vertices");
		assertNull(tr.findIntersections(ray), "infront of one of the vertices");

		// TC13: on the continuation of one of the vertices
		ray = new Ray(Point.ZERO, new Vector(2, -1, 0));

		assertEquals(List.of(new Point(2, -1, 0)), pl.findIntersections(ray),
				"infront of one of the vertices");
		assertNull(tr.findIntersections(ray), "infront of one of the vertices");
	}

	/**
	 * Test method for
	 * {@link geometries.Triangle#findGeoIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindGeoIntersections() {

		Triangle tr = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
		Ray ray;

		// TC01: GeoIntersection is in range distance
		ray = new Ray(new Point(1, 1, 1), new Vector(-1, -1, -1));
		var resultGep = tr.findGeoIntersections(ray, 5);
		var resultPoint = resultGep.get(0).point;

		assertEquals((new Point(1d / 3, 1d / 3, 1d / 3)), resultPoint,
				"intersection inside the range");

		// TC02: GeoIntersection is out of range distance
		assertNull(tr.findGeoIntersections(ray, 0.1), "intersection out of range");

	}

}
