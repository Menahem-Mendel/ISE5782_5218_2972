package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import primitives.*;

public class TubeTest {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Ray, double)}.
	 */
	@RepeatedTest(10)
	public void getNormalTest() {
		// ============ Equivalence Partitions Tests ==============
		// TC01:

		// =============== Boundary Values Tests ==================
		// TC11: Test when connection between the point on the body and the ray’s head
		// creates a 90 degrees with the ray
	}
}
