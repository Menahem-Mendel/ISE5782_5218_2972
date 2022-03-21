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
		Point point1 = new Point(0, 0, 0);
		Vector vector1 = new Vector(1, 0, 0);
		double radius1 = 1.0;
		Ray ray1 = new Ray(point1, vector1);
		Tube tube1 = new Tube(ray1, radius1);

		double alpha = Util.random(-10, 10);
		double y = Math.cos(alpha);
		double z = Math.sqrt(1 - Math.pow(y, 2));

		Point pointCheck = new Point(1, y, z);
		Vector norm = (pointCheck.sub(new Point(1, 0, 0))).normalize();

		assertEquals(norm, tube1.getNormal(pointCheck), "getNormal() wrong normal vector");

		// =============== Boundary Values Tests ==================
		// TC11: Test when connection between the point on the body and the ray's head
		// creates a 90 degrees with the ray

		Point point2 = new Point(0, y, z);

		assertEquals(point2.sub(new Point(0, 0, 0)), tube1.getNormal(point2),
				"getNormal() wrong normal vector when 90 deg with ray");

	}

}
