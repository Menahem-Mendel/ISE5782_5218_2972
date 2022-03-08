package primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class VectorTest {
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
	 * scalar
	 */
	double t;
	/**
	 * lhs base test vector
	 */
	Vector lhs;

	@BeforeEach
	public void init() {
		x = Util.random(MIN, MAX);
		y = Util.random(MIN, MAX);
		z = Util.random(MIN, MAX);

		t = Util.random(MIN, MAX);

		if (Util.isZero(x) && Util.isZero(y) && Util.isZero(z)) {
			x = 1;
			y = 2;
			z = 3;
		}

		if (Util.isZero(t))
			t = 2;

		lhs = new Vector(x, y, z);
	}

	/**
	 * Test method for {@link primitives.Vector#Vector(double, double, double)}.
	 */
	@Test
	public void ctorTest() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Test correct vector
		try {
			new Vector(x, y, z);
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct vector");
		}

		// =============== Boundary Values Tests ==================
		// TC11: Test zero vector
		assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), "Constructed a zero vector");
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@RepeatedTest(10)
	public void crossProductTest() {
		final double ax = Util.random(MIN, MAX);
		final double ay = Util.random(MIN, MAX);
		final double az = Util.random(MIN, MAX);

		Vector rhs = new Vector(ax, ay, az);
		Vector vcp = lhs.crossProduct(rhs);

		final double theta = Math.acos((lhs.dotProduct(rhs) / (lhs.length() * rhs.length())));

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test that length of cross-product is proper
		assertEquals(lhs.length() * rhs.length() * Math.sin(theta), vcp.length(), DELTA,
				"crossProduct() wrong result vector length");

		// TC02: Test cross-product result orthogonality to its operands
		assertTrue(Util.isZero(vcp.dotProduct(lhs)), "crossProduct() result is not orthogonal to 1st operand");
		assertTrue(Util.isZero(vcp.dotProduct(rhs)), "crossProduct() result is not orthogonal to 2nd operand");

		// =============== Boundary Values Tests ==================
		// TC11: Test zero vector from cross-product of co-lined vectors
		Vector vs = lhs.scale(t);
		assertThrows(IllegalArgumentException.class, () -> lhs.crossProduct(vs),
				"crossProduct() for parallel vectors does not throw an exception: lhs: %s, rhs: %s");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@RepeatedTest(10)
	public void dotProductTest() {
		// ============ Equivalence Partitions Tests ==============

		// =============== Boundary Values Tests ==================
		Vector rhs = lhs.scale(t);

		// TC11: Test dot product of two parallel vectors
		assertTrue(Util.isZero(lhs.dotProduct(rhs) - lhs.lengthSquared() * t),
				"dotProduct() for parallel vectors is not squared length");

		rhs = new Vector(-(y + z) * t / x, t, t); // orthogonal vector for lhs

		// TC12: Test dot product of orthogonal vectors
		assertTrue(Util.isZero(lhs.dotProduct(rhs)), "dotProduct() for orthogonal vectors is not zero");
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}
	 */
	@RepeatedTest(10)
	public void scaleTest() {
		Vector rhs = new Vector(x * t, y * t, z * t);

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test scaled vector
		assertEquals(rhs, lhs.scale(t), "scale() wrong scaling vector result");

		// =============== Boundary Values Tests ==================
		// TC11: Test zero scaled vector
		assertThrows(IllegalArgumentException.class, () -> lhs.scale(0),
				"scale() for zero vector doesn't throw an exception");
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@RepeatedTest(10)
	public void normalizeTest() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Test normalized vector
		assertTrue(Util.isZero(lhs.normalize().length() - 1), "normalize() the normalized vector is not a unit vector");

		// TC02: Test normal vector cross product with original vector
		assertThrows(IllegalArgumentException.class, () -> lhs.crossProduct(lhs.normalize()),
				"normalize() the normalized vector is not parallel to the original one");

		// TC03: Test normal vector direction
		assertTrue(lhs.dotProduct(lhs.normalize()) >= 0,
				"normalize() the normalized vector is opposite to the original one");

		// =============== Boundary Values Tests ==================
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@RepeatedTest(10)
	public void lengthTest() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Test vector length
		assertTrue(Util.isZero(lhs.lengthSquared() - (x * x + y * y + z * z)), "length() wrong value");

		// =============== Boundary Values Tests ==================
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@RepeatedTest(10)
	public void addTest() {
		final double ax = Util.random(MIN, MAX);
		final double ay = Util.random(MIN, MAX);
		final double az = Util.random(MIN, MAX);

		Vector rhs = new Vector(ax, ay, az);
		Vector vsum = new Vector(x + ax, y + ay, z + az);

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test vector of sum
		assertEquals(vsum, lhs.add(rhs), "add() wrong vector of sum");

		// =============== Boundary Values Tests ==================
		// TC11: Test vector sum gives zero
		assertThrows(IllegalArgumentException.class, () -> lhs.add(new Vector(-x, -y, -z)),
				"add() for opposite vectors sum doesn't throw an exception");
	}
}
