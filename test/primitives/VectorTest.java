package primitives;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

public class VectorTest {
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
		assertEquals("crossProduct() wrong result length", v1.length() * v2.length(), vr.length(), 0.00001);

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
	public void dotProduct() {
		Vector v1 = new Vector(1, 2, 3);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Test dot product of two parallel vectors
		final double scalar = Math.random() * (1000 + 1000 + 1) - 1000;
		Vector v2 = v1.scale(scalar);

		assertEquals("dotProduct() wrong result of parallel vectors dot product", v1.lengthSquared() * scalar,
				v1.dotProduct(v2), 0.00001);

		// TC02: Test dot product of orthogonal vectors
		Vector v3 = new Vector(0, 0, 1);
		Vector v4 = new Vector(0, 1, 0);
		assertEquals("dotProduct() result should be zero value", 0, v3.dotProduct(v4), 0.00001);

		// =============== Boundary Values Tests ==================
	}
}
