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
		Vector dir = ray.getDir();
		Point p0 = ray.getP0();

		double nv = normal.dot(dir);

		// if ray parallel to plane
		if (Util.isZero(nv)) {
			return null;
		}

		Vector u;
		try {
			u = q0.sub(p0);
		} catch (IllegalArgumentException ignore) {
			return null;
		}

		double t = Util.alignZero(normal.dot(u) / nv);
		return t <= 0 ? null : List.of(ray.getPoint(t));
	}
}
