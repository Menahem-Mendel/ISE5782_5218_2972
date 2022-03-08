package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import primitives.*;

public class TriangleTest {
	final double MIN = -10;
	final double MAX = 10;

	final double DELTA = 0.00001;

	double[] qq = new double[9];
	Point p1, p2, p3;

	Triangle triangle;

	@BeforeEach
	public void init() {
		for (int i = 0; i < 9; i++) {
			qq[i] = Util.random(MIN, MAX);
		}

		p1 = new Point(qq[0], qq[1], qq[2]);
		p2 = new Point(qq[3], qq[4], qq[5]);
		p3 = new Point(qq[6], qq[7], qq[8]);

		triangle = new Triangle(p1, p2, p3);
	}

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
	 */
	@Test
	public void getNormalTest() {
		Vector v1 = p2.subtract(p1);
		Vector v2 = p3.subtract(p1);

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test normal vector
		assertEquals(v1.crossProduct(v2), triangle.getNormal(p1), "getNormal() wrong result normal vector");
		// =============== Boundary Values Tests ==================
	}
}
