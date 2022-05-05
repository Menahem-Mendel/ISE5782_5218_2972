package primitives;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RayTest {

	Point p0 = new Point(0, 0, 0);
	Vector dir = new Vector(1, 0, 0);
	Ray r = new Ray(p0, dir);

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
		assertEquals(p0, r.getPoint(0), "getPoint x0 scale wrong value");
	}

	/**
	 * Test method for{@link primitives.Ray#findClosestPoint(List)}
	 */
	@Test
	public void findClosestPoint() {
		Point expected = new Point(1, 1, 1);
		List<Point> lst;

		// ============ Equivalence Partitions Tests ==============
		// TC01: the closest point is in the middle of the list
		lst = new ArrayList<>() {
			{
				add(new Point(2, 2, 2));
				add(expected);
				add(new Point(3, 3, 3));
			}
		};

		assertEquals(expected, r.findClosestPoint(lst), "got wrong closest point, correct in middle of the list");

		// =============== Boundary Values Tests ==================
		// TC02: an empty list of points
		lst = new ArrayList<>();

		assertNull(r.findClosestPoint(lst), "wrong closest point in empty list");

		// TC03: the closest point is in the head of the list
		lst = new ArrayList<>() {
			{
				add(expected);
				add(new Point(2, 2, 2));
				add(new Point(3, 3, 3));
			}
		};

		assertEquals(expected, r.findClosestPoint(lst), "got wrong closest point, correct in head of the list");

		// TC04: the closest point is in the last point of the list
		lst = new ArrayList<>() {
			{
				add(new Point(2, 2, 2));
				add(new Point(3, 3, 3));
				add(expected);
			}
		};

		assertEquals(expected, r.findClosestPoint(lst), "got wrong closest point, correct in the end of the list");

	}

}