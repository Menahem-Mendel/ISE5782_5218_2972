package geometries;

import static org.junit.jupiter.api.Assertions.*;

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
}
