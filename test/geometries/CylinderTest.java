package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import primitives.*;

public class CylinderTest {
	final double MIN = -10;
	final double MAX = 10;

	final double DELTA = 0.00001;

	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @param z coordinate
	 */
	double x, y, z;

	double r, h;

	double t;

	Point point;
	Vector vec;

	Ray ray;

	Cylinder lhs;

	@BeforeEach
	public void init() {
		x = Util.random(MIN, MAX);
		y = Util.random(MIN, MAX);
		z = Util.random(MIN, MAX);

		point = new Point(x, y, z);

		x = Util.random(MIN, MAX);
		y = Util.random(MIN, MAX);
		z = Util.random(MIN, MAX);

		vec = new Vector(x, y, z).normalize();

		r = Util.random(MIN, MAX);
		h = Util.random(MIN, MAX);

		t = Util.random(1, MAX * MAX);

		ray = new Ray(point, vec);

		lhs = new Cylinder(ray, r, h);
	}

	/**
	 * Test method for
	 * {@link geometries.Cylinder#getNormal(primitives.Ray, double, double)}.
	 */
	@RepeatedTest(10)
	public void getNormalTest() {
		Vector ex = new Vector(1, 0, 0).sub(
				vec.scale(vec.dot(new Vector(1, 0, 0)))).normalize();
		Vector ey = new Vector(0, 1, 0).sub(
				ex.scale(ex.dot(new Vector(0, 1, 0)))).sub(
						vec.scale(vec.dot(new Vector(0, 1, 0))))
				.normalize();

		assertTrue(Util.isZero(vec.dot(ex)), "base vector result is not orthogonal to 1st operand");
		assertTrue(Util.isZero(vec.dot(ey)), "base vector result is not orthogonal to 2nd operand");

		double alpha = 2 * Math.PI * Util.random(0, 1);
		double ax = r * Math.cos(alpha);
		double ay = r * Math.sin(alpha);

		Point rhs = point.add(ex.scale(ax).add(ey.scale(ay)).add(vec.scale(h / t)));
		Vector res = vec.scale(h / t).sub(rhs).scale(-1).normalize();

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test side
		assertEquals(res, lhs.getNormal(rhs), "getNormal() wrong normal vector");

		// TC02: Test base one
		rhs = point.add(ex.scale(ax / t).add(ey.scale(ay / t)));
		res = vec.scale(-1);
		assertEquals(res, lhs.getNormal(rhs), "getNormal() wrong normal vector");

		// TC03: Test base two
		rhs = point.add(ex.scale(ax / t).add(ey.scale(ay / t).add(vec.scale(h))));
		res = vec;
		assertEquals(res, lhs.getNormal(rhs), "getNormal() wrong normal vector");

		// =============== Boundary Values Tests ==================
		// TC11: Test center base one point
		rhs = point;
		res = vec.scale(-1);
		assertEquals(res, lhs.getNormal(rhs), "getNormal() wrong normal vector");

		// TC12: Test center base two point
		rhs = point.add(vec.scale(h));
		res = vec;
		assertEquals(res, lhs.getNormal(rhs), "getNormal() wrong normal vector");
	}
}
