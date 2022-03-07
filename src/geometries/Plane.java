package geometries;

import primitives.*;

/**
 * Plane class represents two-dimensional plane in 3D Cartesian coordinate
 * 
 */
public class Plane implements Geometry {

	private final Point q0;
	private final Vector normal;

	/**
	 * Plane build ctor
	 * 
	 * @param p1 first point
	 * @param p2 second point
	 * @param p3 third point
	 */
	public Plane(Point p1, Point p2, Point p3) {
		Vector a = p2.subtract(p1);
		Vector b = p3.subtract(p1);

		normal = a.crossProduct(b).normalize();
		q0 = p1;
	}

	/**
	 * Plane build ctor
	 * 
	 * @param p point in 3D space
	 * @param n vector normal of the plane
	 */
	public Plane(Point p, Vector n) {
		q0 = p;
		normal = n.normalize();
	}

	/**
	 * getQ0 get center point of the plane
	 * 
	 * @return center point
	 */
	public Point getQ0() {
		return q0;
	}

	/**
	 * getNormal get vector normal of the plane
	 * 
	 * @return vector normal
	 */
	public Vector getNormal() {
		return null;
	}

	/**
	 * getNormal get vector normal of the plane
	 * 
	 * @return vector normal
	 */
	public Vector getNormal(Point p) {
		return null;
	}
}
