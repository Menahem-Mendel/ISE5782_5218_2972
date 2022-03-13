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
		Point center2 = (getAxisRay().getP0()).add((getAxisRay().getDir()).scale(getHeight()));  //second base, adding to center 1 vector direction multiply by height
		Point center1 = (getAxisRay().getP0());   // center of the first base         

		if (Util.isZero(p.dist(center1)) || ((p.sub(center1)).dot(getAxisRay().getDir()) == 0)) {
			return getAxisRay().getDir().scale(-1);
		}
		if (Util.isZero(p.dist(center2)) || ((p.sub(center2)).dot(getAxisRay().getDir()) == 0)) {
			return getAxisRay().getDir();
		} else {
			return super.getNormal(p);
		}
	}
}
