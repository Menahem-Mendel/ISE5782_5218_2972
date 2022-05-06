package renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

/**
 * Integration tests for {@link Camera}
 */
public class CameraIntegrationTest {
	int w = 3;
	int h = 3;

	Vector to = new Vector(0, 0, -1);
	Vector up = new Vector(0, 1, 0);

	Camera cam = new Camera(Point.ZERO, to, up);

	private class Item {
		private int expected;
		private Intersectable geo;

		Item(int expected, Intersectable geo) {
			this.expected = expected;
			this.geo = geo;
		}
	}

	/**
	 * finding a number of integrations
	 * 
	 * @param c camera
	 * @param g intersectable object
	 * @return number of integration points between camera's rays to the intersectable
	 */
	private void assertIntersections(int expected, Camera c, Intersectable g) {
		int count = 0;

		c.setVPDistance(1).setVPSize(w, h);

		for (int i = 0; i < h; ++i)
			for (int j = 0; j < w; ++j) {
				var intersections = g.findIntersections(c.constructRay(w, h, j, i));
				if (intersections != null)
					count += intersections.size();
			}

		assertEquals(expected, count, "Wrong amount of intersections");
	}

	/**
	 * Integration test for ray to {@link Sphere}
	 */
	@Test
	public void cameraRaySphereIntegrationTest() {
		Item items[] = { new Item(2, new Sphere(new Point(0, 0, -3), 1)),
				new Item(18, new Sphere(new Point(0, 0, -3), 2.5)),
				new Item(10, new Sphere(new Point(0, 0, -2.5), 2)),
				new Item(9, new Sphere(new Point(0, 0, -1.5), 4)),
				new Item(0, new Sphere(new Point(0, 0, 0.5), 0.5)), };

		// TC01: small sphere 2 points
		// TC02: big sphere 18 points
		// TC03: medium sphere 10 points
		// TC04: inside sphere 9 points
		// TC05: beyond sphere 0 points
		for (Item item : items)
			assertIntersections(item.expected, cam, item.geo);
	}

	/**
	 * Integration test for ray to {@link Plane}
	 */
	@Test
	public void cameraRayPlaneIntegrationTest() {
		Item items[] = { new Item(9, new Plane(new Point(0, 0, -2.5), new Vector(0, 0, 1))),
				new Item(9, new Plane(new Point(0, 0, -2), new Vector(0, -0.5, 1))),
				new Item(6, new Plane(new Point(0, 0, -3.5), new Vector(0, -1, 1))),
				new Item(0, new Plane(new Point(0, 0, 1), new Vector(0, 0, 1))), };

		// TC01: plane against camera 9 points
		// TC02: plane with small angle 9 points
		// TC03: plane parallel to lower rays 6 points
		// TC04: beyond plane 0 points
		for (Item item : items)
			assertIntersections(item.expected, cam, item.geo);
	}

	/**
	 * Integration test for ray to {@link Triangle}
	 */
	@Test
	public void cameraRayTriangleIntegrationTest() {
		Item items[] = {
				new Item(1,
						new Triangle(new Point(0, 1, -2.5), new Point(1, -1, -2.5),
								new Point(-1, -1, -2.5))),
				new Item(2,
						new Triangle(new Point(0, 20, -2.5), new Point(1, -1, -2.5),
								new Point(-1, -1, -2.5))),
				new Item(0, new Triangle(new Point(0, 20, 1), new Point(1, -1, 1),
						new Point(-1, -1, 1))), };

		// TC01: small triangle 1 point
		// TC02: medium triangle 2 points
		// TC03: beyond triangle 0 points
		for (Item item : items)
			assertIntersections(item.expected, cam, item.geo);
	}
}
