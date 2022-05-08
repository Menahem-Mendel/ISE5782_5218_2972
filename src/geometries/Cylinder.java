package geometries;

import java.util.List;

import primitives.*;

/**
 * Cylinder class represents cylinder in 3D space
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
		Vector dir = axisRay.getDir();
		Point p0 = axisRay.getP0(); // base center
		Point p1 = p0.add(dir.scale(h)); // second base center

		if (Util.isZero(p.dist(p0)) || Util.isZero(p.sub(p0).dot(dir)))
			return dir.scale(-1);
		else if (Util.isZero(p.dist(p1)) || Util.isZero(p.sub(p1).dot(dir)))
			return dir;

		return super.getNormal(p);
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		throw new UnsupportedOperationException("not yet implemented");
	}
}
