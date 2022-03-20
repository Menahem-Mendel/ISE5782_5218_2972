package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import primitives.*;

public class PlaneTest {
	final double MIN = -10;
	final double MAX = 10;

	final double DELTA = 0.00001;

	Point[] pp = new Point[3];

	Plane plane;

	@BeforeEach
	public void init() {
		for (int i = 0; i < 3; i++) {
			double x = Util.random(MIN, MAX);
			double y = Util.random(MIN, MAX);
			double z = Util.random(MIN, MAX);

			pp[i] = new Point(x, y, z);
		}

		plane = new Plane(pp[0], pp[1], pp[2]);
	}

	/**
	 * Test method for
	 * {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}
	 */
	@Test
	public void ctorTest() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: three points test
		try {
			plane = new Plane(pp[0], pp[1], pp[2]);
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct plane");
		}

		// =============== Boundary Values Tests ==================
		// TC11: Test when the first and second points are equal
		assertThrows(IllegalArgumentException.class,
				() -> new Plane(pp[0], pp[0], pp[2]),
				"Constructed a plane when two points are equal");

		// TC12: Test when all points are on the same line
		assertThrows(IllegalArgumentException.class,
				() -> new Plane(pp[0], pp[1], pp[1].add(pp[1].sub(pp[0]))),
				"Constructed a plane when three points on the same line");
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@RepeatedTest(10)
	public void getNormalTest() {
		Vector lhs = pp[1].sub(pp[0]);
		Vector rhs = pp[2].sub(pp[0]);
		Vector vcp = lhs.cross(rhs).normalize();

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test normal vector length and orthogonality
		assertEquals(vcp, plane.getNormal(), "getNormal() wrong normal vector");

		// =============== Boundary Values Tests ==================
	}

	/**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {

		Plane plane = new Plane(new Point(2, 0, 0), new Point(0, 2, 0), new Point(0, 0, 2));
		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray's line is above the plane (but not 90 deg with the plane)(0 points)

		assertNull(plane.findIntersections(new Ray(new Point(3, 3, 0), new Vector(1, 0, 0))),
				"Ray's line out of plane");

		// TC02: Ray's line is below the plane (but not 90 deg with the plane)(1 points)

		Point point = new Point(2, 0, 0);
		assertEquals(point, plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 0, 0))), //
				"findIntersections() wrong point intersection");

		// =============== Boundary Values Tests ==================

		Plane plane2 = new Plane(new Point(1, 0, 1), new Point(0, 1, 1), new Point(-1, 0, 1));

		// **** Group: Ray's line parallel the plane

		// TC11: Ray is outside the plane (0 points)

		assertNull(plane2.findIntersections(new Ray(new Point(1, 1, 2), new Vector(1, 0, 0))),
				"Ray's line out of plane (parallel)");

		// TC12: Ray is inside the plane (0 points)

		assertNull(plane2.findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 0, 0))),
				"Ray's line inside the plane (parallel)");

		// **** Group: Ray's line 90 deg with plane

		// TC13: Ray starts before the plane (1 points)

		Point point2 = new Point(1, 0, 1);
		assertEquals(point2, plane2.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 0, 1))), //
				"findIntersections() wrong point intersection when ray starts befor the plane");

		// TC14: Ray starts at plane (0 points)

		assertNull(plane2.findIntersections(new Ray(new Point(1, 0, 1), new Vector(0, 0, 1))),
				" Ray starts at plane");

		// TC15: Ray starts after plane (0 points)

		assertNull(plane2.findIntersections(new Ray(new Point(1, 0, 2), new Vector(0, 0, 1))),
				"Ray starts after plane");

		// **** Group: Ray's line starts at plane (not parallel and no 90 deg to the
		// plane)
		// TC16: Ray's line starts at plane (not parallel and not 90 deg to the plane)

		assertNull(plane2.findIntersections(new Ray(new Point(1, 0, 1), new Vector(1, 1, 1))),
				" Ray starts at plane (not parallel and not 90 deg to the plane)");

		// **** Group: Ray's line starts at the point which defines the plane
		// TC17: Ray's line starts at the point which defines the plane

		Plane plane3 = new Plane(new Point(1, 1, 1), new Vector(0, 0, 1));
		assertNull(plane3.findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))),
				" Ray starts at p0 who defines the plane");

	}

}
