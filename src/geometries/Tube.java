package geometries;

import java.util.List;

import primitives.*;

/**
 * Tube class represents infinite cylinder in 3D space
 * 
 */
public class Tube implements Geometry {

	private final Ray axisRay;
	private final double radius;

	/**
	 * Tube build ctor
	 * 
	 * @param ray of direction
	 * @param r   radius
	 */
	public Tube(Ray ray, double r) {
		axisRay = ray;
		radius = r;
	}

	/**
	 * getAxisRay returns axis ray
	 * 
	 * @return ray axis
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	/**
	 * getRadius returns radius of the tube base
	 * 
	 * @return radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point p) {
		Vector dir = axisRay.getDir(); // ray direction
		Point p0 = axisRay.getP0();
		Vector u = p.sub(p0); // vector to a point from ray head

		double t = dir.dot(u);
		Point o = axisRay.getPoint(t);

		return p.sub(o).normalize();
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		return null;
	}
}
