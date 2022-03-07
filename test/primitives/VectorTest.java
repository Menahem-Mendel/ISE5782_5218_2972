package primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

public class VectorTest {

	final double MIN = -1000;
	final double MAX = 1000;
	final double DELTA = 0.00001;

	final double q1 = Math.random() * (MAX - MIN + 1) + MIN;
	final double q2 = Math.random() * (MAX - MIN + 1) + MIN;
	final double q3 = Math.random() * (MAX - MIN + 1) + MIN;

	final double scalar = Math.random() * (MAX - MIN + 1) + MIN;

	Vector vec;

	@BeforeEach
	public void setup() {
		vec = new Vector(q1, q2, q3); // base vector
	}

	/**
	 * Test method for {@link primitives.Vector#Vector()}.
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
		Vector v1 = new Vector(1, 2, 3);

		// ============ Equivalence Partitions Tests ==============
		Vector v2 = new Vector(0, 3, -2);
		Vector vr = v1.crossProduct(v2);

		// TC01: Test that length of cross-product is proper (orthogonal vectors taken
		// for simplicity)
		assertEquals(v1.length() * v2.length(), vr.length(), DELTA, "crossProduct() wrong result length");

		// TC02: Test cross-product result orthogonality to its operands
		assertTrue(Util.isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
		assertTrue(Util.isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

		// =============== Boundary Values Tests ==================
		// TC11: test zero vector from cross-productof co-lined vectors
		Vector v3 = new Vector(-2, -4, -6);
		assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
				"crossProduct() for parallel vectors does not throw an exception");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void dotProductTest() {
		Vector vec = new Vector(1, 2, 3);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Test dot product of two parallel vectors
		Vector v2 = vec.scale(scalar);

		assertEquals(vec.lengthSquared() * scalar, vec.dotProduct(v2), DELTA,
				"dotProduct() wrong result of parallel vectors dot product");

		// TC02: Test dot product of orthogonal vectors
		Vector v3 = new Vector(0, 0, 1);
		Vector v4 = new Vector(0, 1, 0);
		assertEquals(0, v3.dotProduct(v4), DELTA, "dotProduct() result should be zero value");

		// =============== Boundary Values Tests ==================
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}
	 */
	@Test
	public void scaleTest() {
		Vector v2 = new Vector(q1 * scalar, q2 * scalar, q3 * scalar);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Test scaled vector
		assertEquals(v2.length(), vec.scale(scalar).length(), DELTA, "scale() wrong scaling vector result");

		// =============== Boundary Values Tests ==================

		// TC11: Test zero scaled vector
		assertThrows(IllegalArgumentException.class, () -> vec.scale(0),
				"sclae() for zero scalar doesn't throw an exception");
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void normalizeTest() {

		// ============ Equivalence Partitions Tests ==============

		// TC01: Test normalized vector
		assertEquals(1, vec.normalize().length(), DELTA, "scale() wrong normal vector");

		// =============== Boundary Values Tests ==================
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void lengthTest() {

		// ============ Equivalence Partitions Tests ==============

		// TC01: test vector length
		assertEquals(Math.sqrt(q1 * q1 + q2 * q2 + q3 * q3), vec.length(), DELTA, "length() wrong vector length");

		// =============== Boundary Values Tests ==================
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void addTest() {
		final double w1 = Math.random() * (MAX - MIN + 1) + MIN;
		final double w2 = Math.random() * (MAX - MIN + 1) + MIN;
		final double w3 = Math.random() * (MAX - MIN + 1) + MIN;

		// ============ Equivalence Partitions Tests ==============
		Vector v2 = new Vector(w1, w2, w3);
		Vector v3 = new Vector(q1 + w1, q2 + w2, q3 + w3);

		// TC01: test vector of sum length
		assertEquals(v3.length(), vec.add(v2).length(), DELTA, "add() wrong vector of sum length");

		// TC02: test vector of sum direction
		assertEquals(v3.lengthSquared(), vec.add(v2).dotProduct(v3), DELTA, "add() wrong vector of sum direction");

		// =============== Boundary Values Tests ==================
		Vector v4 = new Vector(-q1, -q2, -q3);

		assertThrows(IllegalArgumentException.class, () -> vec.add(v4),
				"add() for opposite vectors doesn't throw an exception");
	}
}
