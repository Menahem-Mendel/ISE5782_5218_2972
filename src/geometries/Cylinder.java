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
		Vector ray = getAxisRay().getDir();
		Point center1 = getAxisRay().getP0(); // center of the first base
		Point center2 = getAxisRay().getP0().add(ray.scale(getHeight())); // second base, adding to
																			// center 1 vector direction
																			// multiply by height

		if (Util.isZero(p.dist(center1)) || Util.isZero(p.sub(center1).dot(ray) - 0)) {
			return ray.scale(-1);
		} else if (Util.isZero(p.dist(center2)) || Util.isZero(p.sub(center2).dot(ray) - 0)) {
			return ray;
		} else {
			return super.getNormal(p);
		}
	}
}
