package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import primitives.*;

public class TubeTest {
	final double MIN = -10;
	final double MAX = 10;

	final double DELTA = 0.00001;

	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @param z coordinate
	 */
	double x, y, z;

	double r;

	double t;

	Point point;
	Vector vec;

	Ray ray;

	Tube lhs;

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

		t = Util.random(MIN, MAX);

		ray = new Ray(point, vec);

		lhs = new Tube(ray, r);
	}

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Ray, double)}.
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

		Point rhs = point.add(ex.scale(ax).add(ey.scale(ay)).add(vec.scale(t)));
		Vector res = vec.scale(t).sub(rhs).scale(-1).normalize();

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test general normal
		assertEquals(res, lhs.getNormal(rhs), "getNormal() wrong normal vector");

		// =============== Boundary Values Tests ==================
		// TC11: Test when connection between the point on the body and the ray’s head
		// creates a 90 degrees with the ray
		rhs = point.add(ex.scale(ax).add(ey.scale(ay)));
		res = rhs.sub(new Point(0, 0, 0)).normalize();
		assertEquals(res, lhs.getNormal(rhs), "getNormal() wrong normal vector when 90 deg with ray");
	}
}
