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
		assertTrue("crossProduct() result is not orthogonal to 1st operand", Util.isZero(vr.dotProduct(v1)));
		assertTrue("crossProduct() result is not orthogonal to 2nd operand", Util.isZero(vr.dotProduct(v2)));

		// =============== Boundary Values Tests ==================
		// TC11: test zero vector from cross-productof co-lined vectors
		Vector v3 = new Vector(-2, -4, -6);
		assertThrows("crossProduct() for parallel vectors does not throw an exception", IllegalArgumentException.class,
				() -> v1.crossProduct(v3));
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void dotProductTest() {
		Vector v1 = new Vector(1, 2, 3);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Test dot product of two parallel vectors
		Vector v2 = v1.scale(scalar);

		assertEquals("dotProduct() wrong result of parallel vectors dot product", v1.lengthSquared() * scalar,
				v1.dotProduct(v2), DELTA);

		// TC02: Test dot product of orthogonal vectors
		Vector v3 = new Vector(0, 0, 1);
		Vector v4 = new Vector(0, 1, 0);
		assertEquals("dotProduct() result should be zero value", 0, v3.dotProduct(v4), DELTA);

		// =============== Boundary Values Tests ==================
	}

	@Test
	public void scaleTest() {
		final double scalar = Math.random() * (MAX - MIN + 1) + MIN;

		Vector v1 = new Vector(q1, q2, q3);
		Vector v2 = new Vector(q1 * scalar, q2 * scalar, q3 * scalar);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Test scaled vector
		assertEquals("scale() wrong scaling vector result", v2.length(), v1.scale(scalar).length(), DELTA);

		// =============== Boundary Values Tests ==================
	}

	@Test
	public void normalizeTest() {
		Vector v1 = new Vector(q1, q2, q3);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Test normalized vector
		assertEquals("scale() wrong normal vector", 1, v1.normalize().length(), DELTA);

		// =============== Boundary Values Tests ==================
	}

	@Test
	public void lengthTest() {
		Vector v1 = new Vector(q1, q2, q3);

		// ============ Equivalence Partitions Tests ==============

		// TC01: test vector length
		assertEquals("length()", Math.sqrt(q1 * q1 + q2 * q2 + q3 * q3), v1.length(), DELTA);

		// =============== Boundary Values Tests ==================
	}

	@Test
	public void addTest() {

	}
}
