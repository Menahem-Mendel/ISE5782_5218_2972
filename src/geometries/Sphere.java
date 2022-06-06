package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Sphere class represents a sphere in two-dimensional space
 */
public class Sphere extends Geometry {

	private final Point center;
	private final double radius;
	private final double radius2;

	/**
	 * Sphere build ctor
	 * 
	 * @param p center point
	 * @param r radius
	 */
	public Sphere(Point p, double r) {
		center = p;
		radius = r;
		radius2 = r * r;
	}

	/**
	 * getCenter returns the center point of the sphere
	 * 
	 * @return center point
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * getRadius returns the radius of the sphere
	 * 
	 * @return radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point p) {
		return p.sub(center).normalize();
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		Vector u; // vector from p0 to the center of the sphere

		try {
			u = center.sub(ray.getP0());
		} catch (IllegalArgumentException ignore) {
			return List.of(new GeoPoint(ray.getPoint(radius), this)); // the ray starts at the center
		}

		double tm = ray.getDir().dot(u); // distance from p0 to the center of the chord

		double d2 = alignZero(u.lengthSq() - tm * tm); // squared distance from the center of the sphere to the
														// chord

		double th2 = radius2 - d2; // squared distance from crossed points to the chord

		// if there are no intersections
		if (alignZero(th2) <= 0)
			return null;

		double th = Math.sqrt(th2); // distance from crossed points to the chord
		double t2 = alignZero(tm + th); // distance from p0 to the furhter point
		if (t2 <= 0) // both points are before the ray head
			return null;

		double t1 = alignZero(tm - th); // distance from p0 to the nearer point
		if (alignZero(t1 - maxDistance) > 0) // both points are after the maxDistance on the ray
			return null;

		if (alignZero(t2 - maxDistance) > 0) // 2nd point is after the maxDistance
			return t1 <= 0 ? null : List.of(new GeoPoint(ray.getPoint(t1), this));
		else {
			GeoPoint gp2 = new GeoPoint(ray.getPoint(t2), this);
			return t1 <= 0 ? List.of(gp2) : List.of(new GeoPoint(ray.getPoint(t1), this), gp2);
		}
	}

	@Override
	public void createBox() {
		double x = center.getX();
		double y = center.getY();
		double z = center.getZ();
		this.minX = x - radius;
		this.maxX = x + radius;
		this.minY = y - radius;
		this.maxY = y + radius;
		this.minZ = z - radius;
		this.maxZ = z + radius;
	}
}
