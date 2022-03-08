package primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VectorTest {

	final double MIN = -10;
	final double MAX = 10;

	final double DELTA = 0.00001;

	double q1, q2, q3;
	double scalar;

	Vector lhs;

	@BeforeEach
	public void init() {
		q1 = Util.random(MIN, MAX);
		q2 = Util.random(MIN, MAX);
		q3 = Util.random(MIN, MAX);

		scalar = Util.random(MIN, MAX);

		lhs = new Vector(q1, q2, q3); // base vector
	}

	/**
	 * Test method for {@link primitives.Vector#Vector(double, double, double)}.
	 */
	@Test
	public void ctorTest() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Correct vector
		try {
			new Vector(q1, q2, q3);
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct vector");
		}

		// =============== Boundary Values Tests ==================
		// TC11: Zero vector
		assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), "Constructed a zero vector");

	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void crossProductTest() {
		final double w1 = Util.random(MIN, MAX);
		final double w2 = Util.random(MIN, MAX);
		final double w3 = Util.random(MIN, MAX);

		Vector rhs = new Vector(w1, w2, w3);
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
		Vector vs = lhs.scale(scalar);
		assertThrows(IllegalArgumentException.class, () -> lhs.crossProduct(vs),
				"crossProduct() for parallel vectors does not throw an exception: lhs: %s, rhs: %s");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void dotProductTest() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Test dot product of two parallel vectors
		Vector rhs = lhs.scale(scalar);

		assertEquals(lhs.lengthSquared() * scalar, lhs.dotProduct(rhs), DELTA,
				"dotProduct() wrong result of parallel vectors dot product");

		// TC02: Test dot product of orthogonal vectors
		lhs = new Vector(0, 0, 1);
		rhs = new Vector(1, 1, 0);
		assertEquals(0, lhs.dotProduct(rhs), DELTA, "dotProduct() result should be zero value");

		// =============== Boundary Values Tests ==================
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}
	 */
	@Test
	public void scaleTest() {
		Vector rhs = new Vector(q1 * scalar, q2 * scalar, q3 * scalar);

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test scaled vector
		assertEquals(rhs.length(), lhs.scale(scalar).length(), DELTA, "scale() wrong scaling vector result");

		// =============== Boundary Values Tests ==================
		// TC11: Test zero scaled vector
		assertThrows(IllegalArgumentException.class, () -> lhs.scale(0),
				"scale() for zero scalar doesn't throw an exception");
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void normalizeTest() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Test normalized vector
		assertEquals(1, lhs.normalize().length(), DELTA, "normalize() wrong normal vector");

		// =============== Boundary Values Tests ==================
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void lengthTest() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: test vector length
		assertEquals(q1 * q1 + q2 * q2 + q3 * q3, lhs.lengthSquared(), DELTA, "length() wrong vector length");

		// =============== Boundary Values Tests ==================
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void addTest() {
		final double w1 = Util.random(MIN, MAX);
		final double w2 = Util.random(MIN, MAX);
		final double w3 = Util.random(MIN, MAX);

		Vector rhs = new Vector(w1, w2, w3);
		Vector vs = new Vector(q1 + w1, q2 + w2, q3 + w3);

		// ============ Equivalence Partitions Tests ==============
		// TC01: test vector of sum length
		assertEquals(vs.length(), lhs.add(rhs).length(), DELTA, "add() wrong vector of sum length");

		// TC02: test vector of sum direction
		assertEquals(vs.lengthSquared(), lhs.add(rhs).dotProduct(vs), DELTA, "add() wrong vector of sum direction");

		// =============== Boundary Values Tests ==================
		// TC11: test vector sum gives zero
		assertThrows(IllegalArgumentException.class, () -> lhs.add(new Vector(-q1, -q2, -q3)),
				"add() for opposite vectors sum doesn't throw an exception");
	}
}
