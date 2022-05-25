package geometries;

import primitives.*;
import java.util.List;

/**
 * Intersectable interface describe the intersections with Geomteries variables
 */
public abstract class Intersectable {

	/**
	 * findIntersections finds the intersection points
	 * 
	 * @param ray that goes through geometries
	 * @return List of Points the ray cross the geometry
	 */
	public List<Point> findIntersections(Ray ray) {
		var lst = findGeoIntersections(ray);
		return lst == null ? null : lst.stream().map(gp -> gp.point).toList();
	}

	/**
	 * GeoPoint class represents point with its object
	 */
	public static class GeoPoint {
		public Geometry geometry; // geometry
		public Point point; // point

		/**
		 * GeoPoint build ctor
		 * 
		 * @param p point
		 * @param g geometry
		 */
		public GeoPoint(Point p, Geometry g) {
			point = p;
			geometry = g;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof GeoPoint other))
				return false;
			return geometry == other.geometry && point.equals(other.point);
		}

		@Override
		public String toString() {
			return String.format("GeoPoint [geometry=%s, point=%s]", geometry, point);
		}
	}

	/**
	 * findGeoIntersections finds geometric intersections
	 * 
	 * @param r a given ray
	 * @return list of intersection geopoints
	 */
	public List<GeoPoint> findGeoIntersections(Ray r) {
		return findGeoIntersectionsHelper(r, Double.POSITIVE_INFINITY);
	}

	/**
	 * findGeoIntersections finds geometric intersections with limit of distance
	 * 
	 * @param ray         a given ray
	 * @param maxDistance max distance intersection from geometry
	 * @return list of GeoPoint
	 */
	public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		return findGeoIntersectionsHelper(ray, maxDistance);
	}

	/**
	 * findGeoIntersectionsHelper to find geoIntersections
	 * 
	 * @param ray         a given ray
	 * @param maxDistance max distance point from geometry
	 * @return list of GeoPoint
	 */
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

}
