package renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

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

	Camera cam = new Camera(new Point(0, 0, 0), to, up);

	/**
	 * finding a number of integrations
	 * 
	 * @param c camera
	 * @param g intersectable object
	 * 
	 * @return number of integration points between camera's rays to the
	 *         intersectable
	 */
	private int countIntersections(Camera c, Intersectable g) {
		int count = 0;
		var points = new LinkedList<>();

		c.setVPDistance(1).setVPSize(w, h);

		for (int i = 0; i < h; ++i)
			for (int j = 0; j < w; ++j) {
				var intersections = g.findIntersections(c.constructRay(w, h, j, i));

				if (intersections != null) {

					points.addAll(intersections);
					count += intersections.size();
				}
			}

		return count;
	}

	/**
	 * Integration test for ray to {@link Sphere}
	 */
	@Test
	public void cameraRaySphereIntegrationTest() {
		int expected[] = { 2, 18, 10, 9, 0 };
		Sphere ss[] = {
				new Sphere(new Point(0, 0, -3), 1),
				new Sphere(new Point(0, 0, -3), 2.5),
				new Sphere(new Point(0, 0, -2.5), 2),
				new Sphere(new Point(0, 0, -1.5), 4),
				new Sphere(new Point(0, 0, 0.5), 0.5),
		};

		// TC01: small sphere 2 points
		assertEquals(expected[0], countIntersections(cam, ss[0]), "Wrong amount of intersections");

		// TC02: big sphere 18 points
		assertEquals(expected[1], countIntersections(cam, ss[1]), "Wrong amount of intersections");

		// TC03: medium sphere 10 points
		assertEquals(expected[2], countIntersections(cam, ss[2]), "Wrong amount of intersections");

		// TC04: inside sphere 9 points
		assertEquals(expected[3], countIntersections(cam, ss[3]), "Wrong amount of intersections");

		// TC05: beyond sphere 0 points
		assertEquals(expected[4], countIntersections(cam, ss[4]), "Wrong amount of intersections");
	}

	/**
	 * Integration test for ray to {@link Plane}
	 */
	@Test
	public void cameraRayPlaneIntegrationTest() {
		int expected[] = { 9, 9, 6, 0 };
		Plane pp[] = {
				new Plane(new Point(0, 0, -2.5), new Vector(0, 0, 1)),
				new Plane(new Point(0, 0, -2), new Vector(0, -0.5, 1)),
				new Plane(new Point(0, 0, -3.5), new Vector(0, -1, 1)),
				new Plane(new Point(0, 0, 1), new Vector(0, 0, 1)),
		};

		// TC01: plane against camera 9 points
		assertEquals(expected[0], countIntersections(cam, pp[0]), "Wrong amount of intersections");

		// TC02: plane with small angle 9 points
		assertEquals(expected[1], countIntersections(cam, pp[1]), "Wrong amount of intersections");

		// TC03: plane parallel to lower rays 6 points
		assertEquals(expected[2], countIntersections(cam, pp[2]), "Wrong amount of intersections");

		// TC04: beyond plane 0 points
		assertEquals(expected[3], countIntersections(cam, pp[3]), "Wrong amount of intersections");
	}

	/**
	 * Integration test for ray to {@link Triangle}
	 */
	@Test
	public void cameraRayTriangleIntegrationTest() {
		int expected[] = { 1, 2, 0 };
		Triangle tt[] = {
				new Triangle(new Point(0, 1, -2.5), new Point(1, -1, -2.5), new Point(-1, -1, -2.5)),
				new Triangle(new Point(0, 20, -2.5), new Point(1, -1, -2.5), new Point(-1, -1, -2.5)),
				new Triangle(new Point(0, 20, 1), new Point(1, -1, 1), new Point(-1, -1, 1)),
		};

		// TC01: small triangle 1 point
		assertEquals(expected[0], countIntersections(cam, tt[0]), "Wrong amount of intersections");

		// TC02: medium triangle 2 points
		assertEquals(expected[1], countIntersections(cam, tt[1]), "Wrong amount of intersections");

		// TC02: beyond triangle 0 points
		assertEquals(expected[2], countIntersections(cam, tt[2]), "Wrong amount of intersections");
	}
}
