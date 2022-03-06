package geometries;

import primitives.Ray;

/**
 * Cylinder class represents cylinder in 3D space
 * 
 */
public class Cylinder extends Tube {
	private final double h;

	/**
	 * Cylinder build ctor
	 * 
	 * @param ray ray
	 * @param rad radius
	 * @param h   height
	 */
	public Cylinder(Ray ray, double rad, double h) {
		super(ray, rad);
		this.h = h;
	}

	public double getHeight() {
		return h;
	}
}
