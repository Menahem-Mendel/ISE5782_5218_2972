package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import primitives.*;

public class CylinderTest {

	/**
	 * Test method for
	 * {@link geometries.Cylinder#getNormal(primitives.Ray, double, double)}.
	 */
	@RepeatedTest(1)
	public void getNormalTest() {
		// ============ Equivalence Partitions Tests ==============
		Vector vec1 = new Vector(1, 0, 0);
		Point point1 = new Point(0, 0, 0);
		Ray ray1 = new Ray(point1, vec1);
		Cylinder cylinder1 = new Cylinder(ray1, 1.0, 2.0);

		// TC01: Test side
		Point pointCheck = new Point(1, 1, 0);
		Vector norm = (pointCheck.sub(new Point(1, 0, 0))).normalize();
		assertEquals(norm, cylinder1.getNormal(pointCheck), "getNormal() wrong normal vector");

		// TC02: Test base one
		Point pointCheck2 = new Point(2, 0.1, 0.1);
		Vector vec2 = new Vector(1, 0, 0);
		assertEquals(vec2, cylinder1.getNormal(pointCheck2), "getNormal() wrong normal vector");

		// TC03: Test base two
		Point pointCheck3 = new Point(0, 0.1, 0.1);
		Vector vec3 = new Vector(-1, 0, 0);
		assertEquals(vec3, cylinder1.getNormal(pointCheck3), "getNormal() wrong normal vector");

		// =============== Boundary Values Tests ==================
		// TC11: Test center base one point
		Point pointCheck4 = new Point(2, 0, 0);
		Vector vec4 = new Vector(1, 0, 0);
		assertEquals(vec4, cylinder1.getNormal(pointCheck4), "getNormal() wrong normal vector");

		// TC12: Test center base two point
		Point pointCheck5 = new Point(0, 0, 0);
		Vector vec5 = new Vector(-1, 0, 0);
		assertEquals(vec5, cylinder1.getNormal(pointCheck5), "getNormal() wrong normal vector");
	}

	/**
	 * Test method for {@link geometries.Cylinder#findIntersections(primitives.Ray)}
	 */
	@Test
	public void findIntersectionsTest() {

	}
}