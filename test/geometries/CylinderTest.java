package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;

public class CylinderTest {

	class Item {
		Object expected;
		Object actual;
		String text;

		Item(Object expected, Cylinder cl, Point p, String text) {
			this.expected = expected;
			this.actual = cl.getNormal(p);
			this.text = text;
		}
	};

	/**
	 * Test method for
	 * {@link geometries.Cylinder#getNormal(primitives.Ray, double, double)}.
	 */
	@Test
	public void getNormalTest() {
		Vector vec1 = new Vector(1, 0, 0);
		Point point1 = Point.ZERO;
		Ray ray1 = new Ray(point1, vec1);

		Cylinder cl = new Cylinder(ray1, 1.0, 2.0);

		Item items[] = {
				new Item(new Vector(0, 1, 0), cl, new Point(1, 1, 0),
						"getNormal() wrong normal vector"),
				new Item(new Vector(1, 0, 0), cl, new Point(2, 0.1, 0.1),
						"getNormal() wrong normal vector"),
				new Item(new Vector(-1, 0, 0), cl, new Point(0, 0.1, 0.1),
						"getNormal() wrong normal vector"),
				new Item(new Vector(1, 0, 0), cl, new Point(2, 0, 0),
						"getNormal() wrong normal vector"),
				new Item(new Vector(-1, 0, 0), cl, Point.ZERO,
						"getNormal() wrong normal vector"), };

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test side
		// TC02: Test base one
		// TC03: Test base two
		// =============== Boundary Values Tests ==================
		// TC11: Test center base one point
		// TC12: Test center base two point
		for (var item : items)
			assertEquals(item.expected, item.actual, item.text);
	}
}