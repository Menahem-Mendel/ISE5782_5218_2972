package primitives;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class RayTest {

	class Item {
		Object expected;
		Object actual;
		String text;

		Item(Object expected, List<Point> lst, String text) {
			this.expected = expected;
			this.actual = r.findClosestPoint(lst);
			this.text = text;
		}
	};

	Vector dir = new Vector(1, 0, 0);
	Ray r = new Ray(Point.ZERO, dir);

	/**
	 * Test method for {@link primitives.Ray#getPoint(double)}.
	 */
	@Test
	public void getPointTest() {
		double t = 2d;

		// ============ Equivalence Partitions Tests ==============
		// TC01: getPoint x2 scale directional vector
		assertEquals(new Point(2, 0, 0), r.getPoint(t), "getPoint x2 scale wrong value");

		// =============== Boundary Values Tests ==================
		// TC11: get Point x0 scale directional vector
		assertEquals(Point.ZERO, r.getPoint(0), "getPoint x0 scale wrong value");
	}

	/**
	 * Test method for{@link primitives.Ray#findClosestPoint(List)}
	 */
	@Test
	public void findClosestPoint() {
		Point p1 = new Point(1, 1, 1);
		Point p2 = new Point(2, 2, 2);
		Point p3 = new Point(3, 3, 3);

		Item items[] = {
				new Item(p1, List.of(p2, p1, p3),
						"got wrong closest point, correct in middle of the list"),
				new Item(null, List.of(), "wrong closest point in empty list"),
				new Item(p1, List.of(p1, p2, p3),
						"got wrong closest point, correct in head of the list"),
				new Item(p1, List.of(p2, p3, p1),
						"got wrong closest point, correct in the end of the list") };

		// ============ Equivalence Partitions Tests ==============
		// TC01: the closest point is in the middle of the list
		// =============== Boundary Values Tests ==================
		// TC02: an empty list of points
		// TC03: the closest point is in the head of the list
		// TC04: the closest point is in the last point of the list
		for (var item : items)
			assertEquals(item.expected, item.actual, item.text);
	}
}