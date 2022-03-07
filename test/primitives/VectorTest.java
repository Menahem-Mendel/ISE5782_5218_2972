package primitives;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
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

	Vector vec = new Vector(q1, q2, q3); // base vector

	@Test
	public void VectorTest() {
		
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
		assertEquals("crossProduct() wrong result length", v1.length() * v2.length(), vr.length(), DELTA);

		// TC02: Test cross-product result orthogonality to its operands
		assertTrue("crossProduct() result is not orthogonal to 1st operand", Util.isZero(vr.dotProduct(vec)));
		assertTrue("crossProduct() result is not orthogonal to 2nd operand", Util.isZero(vr.dotProduct(v2)));

		// =============== Boundary Values Tests ==================
		// TC11: test zero vector from cross-productof co-lined vectors
		Vector v3 = new Vector(-2, -4, -6);
		assertThrows("crossProduct() for parallel vectors does not throw an exception", IllegalArgumentException.class,
				() -> vec.crossProduct(v3));
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

		assertEquals("dotProduct() wrong result of parallel vectors dot product", vec.lengthSquared() * scalar,
				vec.dotProduct(v2), DELTA);

		// TC02: Test dot product of orthogonal vectors
		Vector v3 = new Vector(0, 0, 1);
		Vector v4 = new Vector(0, 1, 0);
		assertEquals("dotProduct() result should be zero value", 0, v3.dotProduct(v4), DELTA);

		// =============== Boundary Values Tests ==================
	}

	@Test
	public void scaleTest() {
		Vector v2 = new Vector(q1 * scalar, q2 * scalar, q3 * scalar);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Test scaled vector
		assertEquals("scale() wrong scaling vector result", v2.length(), vec.scale(scalar).length(), DELTA);

		// =============== Boundary Values Tests ==================
	}

	@Test
	public void normalizeTest() {

		// ============ Equivalence Partitions Tests ==============

		// TC01: Test normalized vector
		assertEquals("scale() wrong normal vector", 1, vec.normalize().length(), DELTA);

		// =============== Boundary Values Tests ==================
	}

	@Test
	public void lengthTest() {

		// ============ Equivalence Partitions Tests ==============

		// TC01: test vector length
		assertEquals("length()", Math.sqrt(q1 * q1 + q2 * q2 + q3 * q3), vec.length(), DELTA);

		// =============== Boundary Values Tests ==================
	}

	@Test
	public void addTest() {
		final double w1 = Math.random() * (MAX - MIN + 1) + MIN;
		final double w2 = Math.random() * (MAX - MIN + 1) + MIN;
		final double w3 = Math.random() * (MAX - MIN + 1) + MIN;

		// ============ Equivalence Partitions Tests ==============
		Vector v2 = new Vector(w1, w2, w3);
		Vector v3 = new Vector(q1 + w1, q2 + w2, q3 + w3);

		// TC01: test vector of sum length
		assertEquals("add() wrong vector of sum length", v3.length(), vec.add(v2).length(), DELTA);

		// TC02: test vector of sum direction
		assertEquals("add() wrong vector of sum direction", v3.lengthSquared(), vec.add(v2).dotProduct(v3), DELTA);

		// =============== Boundary Values Tests ==================
		Vector v4 = new Vector(-q1, -q2, -q3);

		assertThrows("add() for opposite vectors doesn't throw an exception", IllegalArgumentException.class,
				() -> vec.add(v4));
	}
}
