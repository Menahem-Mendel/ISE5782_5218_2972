package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;

public class TubeTest {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Ray, double)}.
	 */
	@Test
	public void getNormalTest() {
		Point point = Point.ZERO;
		Vector vec = new Vector(1, 0, 0);
		double rad = 1.0;
		Ray ray = new Ray(point, vec);
		Tube tube = new Tube(ray, rad);

		double alpha = Util.random(-10, 10);
		double y = Math.cos(alpha);
		double z = Math.sqrt(1 - Math.pow(y, 2));

		Point p = new Point(1, y, z);
		Vector norm = new Vector(0, y, z).normalize();

		// ============ Equivalence Partitions Tests ==============
		// TC01: Basic test
		assertEquals(norm, tube.getNormal(p), "getNormal() wrong normal vector");

		// =============== Boundary Values Tests ==================
		// TC11: Test when connection between the point on the body and the ray's head
		// creates a 90 degrees with the ray
		point = new Point(0, y, z);

		assertEquals(point.sub(Point.ZERO), tube.getNormal(point),
				"getNormal() wrong normal vector when 90 deg with ray");
	}
}
