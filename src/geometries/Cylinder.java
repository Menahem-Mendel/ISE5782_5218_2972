package geometries;

import java.util.List;

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
		Vector dir = getAxisRay().getDir();
		Point center1 = getAxisRay().getP0();
		Point center2 = center1.add(dir.scale(h)); // second base, adding to center 1 vector direction multiply by
													// height

		if (Util.isZero(p.dist(center1)) || Util.isZero(p.sub(center1).dot(dir))) {
			return dir.scale(-1);
		} else if (Util.isZero(p.dist(center2)) || Util.isZero(p.sub(center2).dot(dir))) {
			return dir;
		} else {
			return super.getNormal(p);
		}
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		return null;
	}
}
