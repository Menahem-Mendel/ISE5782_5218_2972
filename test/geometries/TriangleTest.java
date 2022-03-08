package geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import primitives.*;

public class TriangleTest {
	final double MIN = -10;
	final double MAX = 10;

	final double DELTA = 0.00001;

	Point[] pp = new Point[3];

	Triangle triangle;

	@BeforeEach
	public void init() {
		for (int i = 0; i < 3; i++) {
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
		Vector lhs = pp[1].subtract(pp[0]);
		Vector rhs = pp[2].subtract(pp[0]);
		Vector vcp = lhs.crossProduct(rhs).normalize();
		Random r = new Random();

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test normal vector
		assertEquals(vcp.lengthSquared(), vcp.dotProduct(triangle.getNormal(pp[r.nextInt(3)])), DELTA,
				"getNormal() wrong result normal vector");

		// =============== Boundary Values Tests ==================
	}
}
