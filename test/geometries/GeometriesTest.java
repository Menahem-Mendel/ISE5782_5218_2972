package geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import primitives.*;

public class GeometriesTest {
	class Item {
		List<Point> expected;
		List<Point> actual;
		String text;

		Item(List<Intersectable> lst, Ray r, String text) {
			if (lst == null)
				return;

			for (Intersectable g : lst) {
				var intersections = g.findIntersections(r);

				if (intersections == null)
					continue;

				if (expected == null)
					expected = new LinkedList<>();

				expected.addAll(intersections);
			}

			actual = new Geometries(lst.toArray(new Intersectable[lst.size()])).findIntersections(r);
			this.text = text;
		}
	};

	/**
	 * Test method for
	 * {@link geometries.Geometries#findIntersections(primitives.Ray)}
	 */
	@Test
	public void findIntersectionsTest() {
		Triangle tr = new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
		Sphere sp = new Sphere(new Point(5, 5, 5), 3);
		Plane pln = new Plane(new Point(-1, 0, 0), new Point(0, -1, 0), new Point(0, 0, -1));

		Item items[] = {
				new Item(List.of(tr, pln, sp), new Ray(Point.ZERO, new Vector(1, 1, 1)),
						"Ray's line goes through few geometries but not all of them"),
				new Item(List.of(), new Ray(Point.ZERO, new Vector(1, 1, 1)),
						"empty collection of geometries"),
				new Item(List.of(tr, pln, sp), new Ray(Point.ZERO, new Vector(-1, 1, 1)),
						"Ray's line does not go through any geometry in the collection"),
				new Item(List.of(tr, pln, sp), new Ray(Point.ZERO, new Vector(-1, -1, -1)),
						"Ray's line goes through only one geometry element in the collection"),
				new Item(List.of(tr, pln, sp), new Ray(new Point(-2, -2, -2), new Vector(1, 1, 1)),
						"Ray's line goes through all elements in the collection"), };

		// ============ Equivalence Partitions Tests ==============
		// TC01: Ray's line goes through few geometries but not all of them
		// =============== Boundary Values Tests ==================
		// TC11: empty collection of geometries
		// TC12: Ray's line does not go through any geometry in the collection
		// TC13: Ray's line goes through only one geometry element in the collection
		// TC14: Ray's line goes through all elements in the collection
		for (var item : items)
			if (item.expected != null) {
				assertEquals(item.expected.size(), item.actual.size(), "wrong amount of intersections");
				assertTrue(item.expected.containsAll(item.actual), item.text);
			} else
				assertNull(item.actual, item.text);
	}
}
