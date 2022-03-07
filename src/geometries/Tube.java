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
	 * @param ray
	 * @param r
	 */
	public Tube(Ray ray, double r) {
		axisRay = ray;
		radius = r;
	}

	/**
	 * getAxisRay get axis ray
	 * 
	 * @return ray axis
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	/**
	 * getRadius get radius of the tube base
	 * 
	 * @return radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point p) {
		return null;
	}

}
