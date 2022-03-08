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

	@Test
	public void ctorTest() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: three points test

		// =============== Boundary Values Tests ==================

		// TC11: Test when the first and second points are equal

		// TC12: Test when all points are on the same line
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@RepeatedTest(10)
	public void getNormalTest() {
		Vector lhs = pp[1].subtract(pp[0]);
		Vector rhs = pp[2].subtract(pp[0]);
		Vector vcp = lhs.crossProduct(rhs).normalize();

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test normal vector length
		assertEquals(vcp.length(), plane.getNormal().length(),
				"getNormal() wrong normal vector length");

		// TC02: Test normal vector orthogonality
		assertEquals(vcp.length(), vcp.dotProduct(plane.getNormal()),
				"getNormal() wrong normal vector direction");

		// =============== Boundary Values Tests ==================
	}
}
