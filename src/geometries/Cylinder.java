package geometries;

import primitives.Errors;
import primitives.Ray;
import primitives.Util;

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

	/**
	 * getHeight get the height of the cylinder
	 * 
	 * @return height
	 */
	public double getHeight() {
		return h;
	}
}
