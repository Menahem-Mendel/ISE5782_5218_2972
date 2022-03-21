package geometries;

import java.util.LinkedList;
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
	 * @throws IllegalArgumentException when the points are on the same line
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
	 * getQ0 returns the center point of the plane
	 * 
	 * @return center point
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
		return normal;
	}

	@Override
	public List<Point> findIntersections(Ray ray) {

		double nv = normal.dot(ray.getDir());
		if (Util.isZero(nv)) { // if ray parallel to plane
			return null;

		}
		if (Util.alignZero(ray.getP0().dist(q0)) == 0) {
			return null;
		}
		if (Util.alignZero((ray.getP0().sub(q0)).dot(normal)) == 0) {
			return null;
		}

		double t = Util.alignZero(normal.dot(q0.sub(ray.getP0())) / nv);
		if (t > 0) {
			Point p = ray.getP0().add(ray.getDir().scale(t));
			List<Point> result = new LinkedList<>();
			result.add(p);
			return result;
		} else {
			return null;
		}

	}
}
