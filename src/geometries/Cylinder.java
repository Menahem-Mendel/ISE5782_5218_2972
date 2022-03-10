package geometries;

import primitives.*;

/**
 * Cylinder class represents cylinder in 3D space
 * 
 */
public class Cylinder extends Tube {

	private final double h;

	/**
	 * Cylinder build ctor
	 * 
	 * @param ray of direction
	 * @param rad radius
	 * @param h   height
	 */
	public Cylinder(Ray ray, double rad, double h) {
		super(ray, rad);
		this.h = h;
	}

	/**
	 * getHeight returns the height of the cylinder
	 * 
	 * @return height
	 */
	public double getHeight() {
		return h;
	}

	@Override
	public Vector getNormal(Point p) {
		return null;
	}
}
