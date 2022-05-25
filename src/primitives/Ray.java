package primitives;

import java.util.List;

import geometries.Intersectable.GeoPoint;

/**
 * Ray represents directional vector which starts from the starting point
 */
public class Ray {
	private static final double EPS = 0.1; // moving the intersect point with dist of delta

	private final Point p0; // start
	private final Vector dir; // direction

	/**
	 * Ray build ctor
	 * 
	 * @param p starting point
	 * @param v directional vector
	 */
	public Ray(Point p, Vector v) {
		dir = v.normalize();
		p0 = p;
	}

	/**
	 * ctor with moving the head of the ray with delta
	 * 
	 * @param point point
	 * @param v     direction
	 * @param n     vector for the line to move the head on
	 */
	public Ray(Point point, Vector v, Vector n) {
		p0 = point.add(n.scale(n.dot(v) > 0 ? EPS : -EPS));
		dir = v;
	}

	/**
	 * getP0 returns starting point
	 * 
	 * @return starting point
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * getDir returns directional vector
	 * 
	 * @return vector direction
	 */
	public Vector getDir() {
		return dir;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Ray other))
			return false;
		return p0.equals(other.p0) && dir.equals(other.dir);
	}

	@Override
	public String toString() {
		return String.format("%s, %s", p0, dir);
	}

	/**
	 * getPoint get the point on the line (p0 + t*dir)
	 * 
	 * @param t dir scale
	 * @return point on the line
	 */
	public Point getPoint(double t) {
		return Util.isZero(t) ? p0 : p0.add(dir.scale(t));
	}

	/**
	 * findClosestPoint finds closest point to the ray's head
	 * 
	 * @param lst list of points
	 * @return closest point to the ray's head
	 */
	public Point findClosestPoint(List<Point> points) {
		return points == null || points.isEmpty() ? null
				: findClosestGeoPoint(points.stream().map(p -> new GeoPoint(p, null)).toList()).point;
	}

	/**
	 * findClosestGeoPoint finds closest geo point to the ray's head
	 * 
	 * @param lst list of geo points
	 * @return closest geo point to the ray's head
	 */
	public GeoPoint findClosestGeoPoint(List<GeoPoint> lst) {
		if (lst == null || lst.isEmpty())
			return null;

		GeoPoint closest = lst.get(0);

		for (GeoPoint gp : lst)
			if (closest.point.distSq(p0) > gp.point.distSq(p0))
				closest = gp;

		return closest;
	}
}
