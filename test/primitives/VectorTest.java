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

		lhs = new Vector(x, y, z);
	}

	/**
	 * Test method for {@link primitives.Vector#Vector(double, double, double)}.
	 */
	@Test
	public void ctorTest() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Test correct vector
		assertDoesNotThrow(() -> new Vector(x, y, z), "Failed constructing a correct vector");

		// =============== Boundary Values Tests ==================
		// TC11: Test zero vector
		assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0),
				"Constructed a zero vector");
	}

	/**
	 * Test method for {@link primitives.Vector#cross(primitives.Vector)}.
	 */
	@RepeatedTest(10)
	public void crossTest() {
		final double ax = Util.random(MIN, MAX);
		final double ay = Util.random(MIN, MAX);
		final double az = Util.random(MIN, MAX);

		Vector rhs = new Vector(ax, ay, az);
		Vector vcp = lhs.cross(rhs);

		final double theta = Math.acos((lhs.dot(rhs) / (lhs.length() * rhs.length())));

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test that length of cross-product is proper
		assertEquals(lhs.length() * rhs.length() * Math.sin(theta), vcp.length(), DELTA,
				"cross() wrong result vector length");

		// TC02: Test cross-product result orthogonality to its operands
		assertEquals(0, vcp.dot(lhs), DELTA, "cross() result is not orthogonal to 1st operand");
		assertEquals(0, vcp.dot(rhs), DELTA, "cross() result is not orthogonal to 2nd operand");

		// =============== Boundary Values Tests ==================
		// TC11: Test zero vector from cross-product of co-lined vectors
		Vector vs = lhs.scale(t);
		assertThrows(IllegalArgumentException.class, () -> lhs.cross(vs),
				"cross() for parallel vectors does not throw an exception: lhs: %s, rhs: %s");
	}

	/**
	 * Test method for {@link primitives.Vector#dot(primitives.Vector)}.
	 */
	@RepeatedTest(10)
	public void dotTest() {
		final double ax = Util.random(MIN, MAX);
		final double ay = Util.random(MIN, MAX);
		final double az = Util.random(MIN, MAX);

		Vector rhs = new Vector(ax, ay, az);
		double dp = x * ax + y * ay + z * az;

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test dot product for different vectors
		assertEquals(dp, lhs.dot(rhs), DELTA, "dot() wrong result");

		// =============== Boundary Values Tests ==================
		// TC11: Test dot product of two parallel vectors
		rhs = lhs.scale(t);

		assertEquals(lhs.lengthSq() * t, lhs.dot(rhs), DELTA,
				"dot() for parallel vectors is not squared length");

		// TC12: Test dot product of orthogonal vectors
		rhs = new Vector(-(y + z) * t / x, t, t); // orthogonal vector for lhs

		assertEquals(0, lhs.dot(rhs), DELTA, "dot() for orthogonal vectors is not zero");
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
		assertEquals(1, lhs.normalize().length(), DELTA,
				"normalize() the normalized vector is not a unit vector");

		// TC02: Test normal vector cross product with original vector
		assertThrows(IllegalArgumentException.class, () -> lhs.cross(lhs.normalize()),
				"normalize() the normalized vector is not parallel to the original one");

		// TC03: Test normal vector direction
		assertFalse(lhs.dot(lhs.normalize()) < 0,
				"normalize() the normalized vector is opposite to the original one");
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@RepeatedTest(10)
	public void lengthTest() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Test vector length
		assertEquals(Math.sqrt(x * x + y * y + z * z), lhs.length(), DELTA, "length() wrong value");
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSq()}.
	 */
	@RepeatedTest(10)
	public void lengthSquaredTest() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Test vector length squared
		assertEquals(x * x + y * y + z * z, lhs.lengthSq(), DELTA, "lengthSquared() wrong value");
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
