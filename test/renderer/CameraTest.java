package renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;

/**
 * Testing Camera Class
 * 
 * @author Dan
 *
 */
class CameraTest {
	static final Point ZERO = new Point(0, 0, 0);

	/**
	 * Test method for
	 * {@link elements.Camera#constructRay(int, int, int, int)}.
	 */
	@Test
	void ctorRayTest() {
		Camera camera = new Camera(ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0)).setVPDistance(10);

		class Item {
			Ray expected;
			Ray actual;

			Item(Ray expected, Ray actual) {
				this.expected = expected;
				this.actual = actual;
			}
		}

		Item items[] = {
				new Item(
						new Ray(ZERO, new Vector(1, -1, -10)),
						camera.setVPSize(8, 8).constructRay(4, 4, 1, 1)),
				new Item(
						new Ray(ZERO, new Vector(0, 0, -10)),
						camera.setVPSize(6, 6).constructRay(3, 3, 1, 1)),
				new Item(
						new Ray(ZERO, new Vector(0, -2, -10)),
						camera.setVPSize(6, 6).constructRay(3, 3, 1, 0)),
				new Item(
						new Ray(ZERO, new Vector(2, 0, -10)),
						camera.setVPSize(6, 6).constructRay(3, 3, 0, 1)),
				new Item(
						new Ray(ZERO, new Vector(2, -2, -10)),
						camera.setVPSize(6, 6).constructRay(3, 3, 0, 0)),
				new Item(
						new Ray(ZERO, new Vector(3, -3, -10)),
						camera.setVPSize(8, 8).constructRay(4, 4, 0, 0)),
				new Item(
						new Ray(ZERO, new Vector(1, -3, -10)),
						camera.setVPSize(8, 8).constructRay(4, 4, 1, 0)),
		};

		// ============ Equivalence Partitions Tests ==============
		// EP01: 4X4 Inside (1,1)
		// =============== Boundary Values Tests ==================
		// BV01: 3X3 Center (1,1)
		// BV02: 3X3 Center of Upper Side (0,1)
		// BV03: 3X3 Center of Left Side (1,0)
		// BV04: 3X3 Corner (0,0)
		// BV05: 4X4 Corner (0,0)
		// BV06: 4X4 Side (0,1)
		for (Item item : items)
			assertEquals(item.expected, item.actual, "bad ray");
	}
}
