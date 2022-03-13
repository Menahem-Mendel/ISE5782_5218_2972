package primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.BeforeEach;

public class PointTest {
	final double MIN = -10;
	final double MAX = 10;

	final double DELTA = 0.00001;

	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @param z coordinate
	 */
	double x, y, z;
	/**
	 * lhs base test vector
	 */
	Point lhs;

	@BeforeEach
	public void init() {
		x = Util.random(MIN, MAX);
		y = Util.random(MIN, MAX);
		z = Util.random(MIN, MAX);

		lhs = new Point(x, y, z);
	}

	/**
	 * Test Method for {@link primitives.Point#add(primitives.Vector)}
	 */
	@RepeatedTest(10)
	public void addTest() {
		final double ax = Util.random(MIN, MAX);
		final double ay = Util.random(MIN, MAX);
		final double az = Util.random(MIN, MAX);

		Vector rhs = new Vector(ax, ay, az);

		// ============ Equivalence Partitions Tests ==============
		// TC01: add general tests
		assertEquals(new Point(rhs.xyz.d1 + lhs.xyz.d1, rhs.xyz.d2 + lhs.xyz.d2, rhs.xyz.d3 + lhs.xyz.d3), lhs.add(rhs),
				"add() problem adding a vector to a point");
	}

	/**
	 * Test Method for {@link primitives.Point#sub(primitives.Point)}
	 */
	@RepeatedTest(10)
	public void subTest() {
		final double ax = Util.random(MIN, MAX);
		final double ay = Util.random(MIN, MAX);
		final double az = Util.random(MIN, MAX);

		Point rhs = new Point(ax, ay, az);
		Vector res = new Vector(x - ax, y - ay, z - az);

		// ============ Equivalence Partitions Tests ==============
		// TC01: subtract general test
		assertEquals(res, lhs.sub(rhs), "sub() wrong result");

		// =============== Boundary Values Tests ==================
		// TC11: subtract the point itself, so the point of vector might be (0,0,0)
		assertThrows(IllegalArgumentException.class, () -> lhs.sub(lhs), "sub() can't create zero vector");

		// TC12: point subtract (0,0,0)
		res = new Vector(x, y, z);
		assertEquals(res, lhs.sub(new Point(0, 0, 0)), "sub() the vector should have the same value as the point");

		// TC13: (0,0,0) subtract a point
		res = new Vector(-x, -y, -z);
		assertEquals(res, new Point(0, 0, 0).sub(lhs), "sub() the vector should have the opposite value of the point");

		// TC14: point subtract the opposite point
		res = new Vector(2 * x, 2 * y, 2 * z);
		assertEquals(res, lhs.sub(new Point(lhs.xyz.scale(-1))),
				"sub() the vector should contain a point double size then original point");
	}

	/**
	 * Test Method for {@link primitives.Point#dist(primitives.Point)}
	 */
	@RepeatedTest(10)
	public void distTest() {
		final double ax = Util.random(MIN, MAX);
		final double ay = Util.random(MIN, MAX);
		final double az = Util.random(MIN, MAX);

		Point rhs = new Point(ax, ay, az);

		double res = Math.sqrt((x - ax) * (x - ax) + (y - ay) * (y - ay) + (z - az) * (z - az));

		// ============ Equivalence Partitions Tests ==============
		// TC01: distance of two points
		assertEquals(res, lhs.dist(rhs), DELTA, "dist() wrong result");

		// =============== Boundary Values Tests ==================
		// TC11: distance from a point to the same point
		assertTrue(Util.isZero(lhs.dist(lhs) - 0), "dist() distance from point to himself should be 0");

		// TC12: distance of opposite points
		rhs = new Point(-x, -y, -z);
		assertEquals(2 * lhs.dist(new Point(0, 0, 0)), lhs.dist(rhs), DELTA, "dist() wrong distance calculating");
	}

	/**
	 * Test Method for {@link primitives.Point#distSquared(primitives.Point)}
	 */
	@RepeatedTest(10)
	public void distSquaredTest() {
		final double ax = Util.random(MIN, MAX);
		final double ay = Util.random(MIN, MAX);
		final double az = Util.random(MIN, MAX);

		Point rhs = new Point(ax, ay, az);

		double res = (x - ax) * (x - ax) + (y - ay) * (y - ay) + (z - az) * (z - az);

		// ============ Equivalence Partitions Tests ==============
		// TC01: squared distance of two points
		assertTrue(Util.isZero(lhs.distSquared(rhs) - res), "distSquared() wrong result");

		// =============== Boundary Values Tests ==================
		// TC11: squared distance from a point to the same point
		assertTrue(Util.isZero(lhs.distSquared(lhs) - 0), "distSquared() distance from point to himself should be 0");

		// TC12: squared distance of opposite points
		rhs = new Point(-x, -y, -z);
		assertEquals(4 * lhs.distSquared(new Point(0, 0, 0)), lhs.distSquared(rhs), DELTA,
				"distSquared() wrong distance calculating");
	}
}