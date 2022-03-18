package geometries;

import java.util.List;

import primitives.*;

/**
 * Triangle class represents triangl in 3D space
 * 
 */
public class Triangle extends Polygon {

	/**
	 * Triangle represents triangle in 3D space
	 * 
	 * @param p1 vertex
	 * @param p2 vertex
	 * @param p3 vertex
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
	}

	@Override
	public List<Point> findIntersections (Ray ray) {
		return null;
	}

}
