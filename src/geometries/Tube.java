package geometries;

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
		Vector ray = getAxisRay().getDir(); // ray
		Vector point = p.sub(getAxisRay().getP0()); // vector to a point from ray head

		if (Util.isZero(ray.dot(point))) {
			return p.sub(new Point(0, 0, 0)).normalize();
		}

		Vector proj = ray.scale(point.dot(ray));
		return p.sub(new Point(0, 0, 0)).sub(proj).normalize();
	}
}
