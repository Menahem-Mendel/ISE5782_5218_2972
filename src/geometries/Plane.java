package geometries;

import java.util.List;

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
		Vector a = p2.sub(p1);
		Vector b = p3.sub(p1);

		normal = a.cross(b).normalize();
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
	 * getQ0 returns the referense point of the plane
	 * 
	 * @return referense point
	 */
	public Point getQ0() {
		return q0;
	}

	/**
	 * getNormal returns the plane normal vector
	 * 
	 * @return vector normal
	 */
	public Vector getNormal() {
		return normal;
	}

	@Override
	public Vector getNormal(Point p) {
		return getNormal();
	}

	@Override
	public List<Point> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
