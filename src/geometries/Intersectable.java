package geometries;

import java.util.*;
import java.util.stream.Collectors;

import primitives.*;

/**
 * Intersectable interface describe the intersections with Geomteries variables + methods camelCase variables: class (static), instance (object), local,
 * parameters variables - main word is a subject, methods - main word is a verb types CamelCase CONSTANT_NAMES packagesnames
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

		return lst == null ? null : lst.stream().map(gp -> gp.point).collect(Collectors.toList());
	}

	/**
	 * GeoPoint class represents ...
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
			if (obj == null || !(obj instanceof GeoPoint other))
				return false;
			return point.equals(other.point) && geometry.equals(other.geometry);
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
		return findGeoIntersectionsHelper(r);
	}

	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray r);
}
