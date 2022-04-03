package primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RayTest {

	/**
	 * Test method for {@link primitives.Ray#getPoint(double)}.
	 */
	@Test
	public void getPointTest() {
		Point p0 = new Point(0, 0, 0);
		Vector dir = new Vector(1, 0, 0);
		Ray r = new Ray(p0, dir);
		double t = 2d;

		// TC01: getPoint x2 scale directional vector
		assertEquals(new Point(2, 0, 0), r.getPoint(t), "getPoint x2 scale wrong value");

		// =============== Boundary Values Tests ==================
		// TC02: get Point x0 scale directional vector
		assertEquals(p0, r.getPoint(0), "getPoint x0 scale wrong value");
	}
}