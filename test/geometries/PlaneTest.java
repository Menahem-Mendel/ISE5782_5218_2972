package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import primitives.*;

public class PlaneTest {
	final double MIN = -10;
	final double MAX = 10;

	final double DELTA = 0.00001;

	Point[] pp = new Point[3];

	Plane plane;

	@BeforeEach
	public void init() {
		for (int i = 0; i < 3; i++) {
			double x = Util.random(MIN, MAX);
			double y = Util.random(MIN, MAX);
			double z = Util.random(MIN, MAX);

			pp[i] = new Point(x, y, z);
		}

		plane = new Plane(pp[0], pp[1], pp[2]);
	}

	/**
	 * Test method for
	 * {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}
	 */
	@Test
	public void ctorTest() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: three points test
		try {
			plane = new Plane(pp[0], pp[1], pp[2]);
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct plane");
		}

		// =============== Boundary Values Tests ==================
		// TC11: Test when the first and second points are equal
		assertThrows(IllegalArgumentException.class,
				() -> new Plane(pp[0], pp[0], pp[2]),
				"Constructed a plane when two points are equal");

		// TC12: Test when all points are on the same line
		assertThrows(IllegalArgumentException.class,
				() -> new Plane(pp[0], pp[1], pp[1].add(pp[1].sub(pp[0]))),
				"Constructed a plane when three points on the same line");
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@RepeatedTest(10)
	public void getNormalTest() {
		Vector lhs = pp[1].sub(pp[0]);
		Vector rhs = pp[2].sub(pp[0]);
		Vector vcp = lhs.cross(rhs).normalize();

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test normal vector length and orthogonality
		assertEquals(vcp, plane.getNormal(), "getNormal() wrong normal vector");

		// =============== Boundary Values Tests ==================
	}

	/**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
       

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is above the plane (but not 90 deg with the plane)(0 points)
       

        // TC02: Ray's line is below the plane (but not 90 deg with the plane)(1 points)
       
        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line parallel the plane 
        // TC11: Ray is outside the plane (0 points)
        // TC12: Ray is inside the plane (0 points)

        // **** Group: Ray's line 90 deg with plane
        // TC13: Ray starts before the plane (1 points)
        // TC14: Ray starts at plane (0 points)
        // TC15: Ray starts after plane (0 points)

        // **** Group: Ray's line starts at plane (not parallel and no 90 deg to the plane)
        // TC16:  Ray's line starts at plane (not parallel and no 90 deg to the plane)
    

        // **** Group:  Ray's line starts at the point which defines the plane 
        // TC17:  Ray's line starts at the point which defines the plane 

    }

}
