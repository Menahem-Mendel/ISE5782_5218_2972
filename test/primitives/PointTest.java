package primitives;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class PointTest {

	/**
	 * Test Method for {@link primitives.Point#add(Vector rhs)}
	 */
	@RepeatedTest(10)
	public void addTest() {
		Point point =new Point( Util.random(-100, 100),  Util.random(-100, 100),  Util.random(-100, 100));
		Vector vector =new Vector( Util.random(-100, 100),  Util.random(-100, 100),  Util.random(-100, 100));
		try {
			Point result = point.add(vector);

			assertTrue(result.equals(new Point(vector.xyz.d1 + point.xyz.d1,vector.xyz.d2 + point.xyz.d2, vector.xyz.d3 + point.xyz.d3))//
		," Porblem in adding a vector to a point");
		} catch (IllegalArgumentException e) {
			fail("Failed creating a new Point");
		}
	}

	/**
	 * Test Method for {@link primitives.Point#sub(Point rhs)}
	 */
	@RepeatedTest(10)
	public void subTest() {
		Point point =new Point( Util.random(-100, 100),  Util.random(-100, 100),  Util.random(-100, 100));


		// TC01: subtract the point itself, so the point of vector might be (0,0,0)
		assertThrows(IllegalArgumentException.class, () -> point.sub(point),
		"can not creating vector (0,0,0) ");

		// TC02: point subtract (0,0,0)
		Vector vec =point.sub(new Point(0,0,0));
		assertTrue((vec.xyz).equals(point.xyz) , "the vector should have the same value as the point ");

		// TC02:  (0,0,0) subtract a point
		Vector vec1 =(new Point(0,0,0).sub(point));
		assertTrue((vec1.xyz).equals(point.xyz.scale(-1)) , "the vector should have the opposite value of the point");

		// TC02:  point subtract the opposite point
		Vector vec2 =point.sub(new Point (point.xyz.scale(-1)));
		assertTrue((vec2.xyz).equals(point.xyz.scale(2)) , "the vactor should contain a point double size then original point");

		
	}


	/**
	 * Test Method for {@link primitives.Point#dist(Point rhs)}
	 */
	@RepeatedTest(10)
	public void distTest() {

		// distance from a point to the same point
		Point point1 =new Point( Util.random(-100, 100),  Util.random(-100, 100),  Util.random(-100, 100));
		assertTrue((point1.dist(point1))==0, "distance from point to himself should be 0 ");

		//distance of two point on the same line
		double random =Util.random(-100, 100);
		Point point2 =new Point( random, 0,  0);
		Point point3 =new Point( -random, 0,  0);
        assertTrue(point2.dist(point3)==Math.abs(2*random), " wrong distance calculating");


	}
}