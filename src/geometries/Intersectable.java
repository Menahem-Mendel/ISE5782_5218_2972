package geometries;

import primitives.*;
import java.util.List;

/**
 * Intersectable interface describe the intersections with Geomteries variables
 */
public abstract class Intersectable {

	/**
     * Minimum of x
     */
    protected double minX = Double.POSITIVE_INFINITY;
    /**
     * Minimum of y
     */
    protected double minY = Double.POSITIVE_INFINITY;
    /**
     * Minimum of z
     */
    protected double minZ = Double.POSITIVE_INFINITY;
    /**
     * Maximum of x
     */
    protected double maxX = Double.NEGATIVE_INFINITY;
    /**
     * Maximum of y
     */
    protected double maxY = Double.NEGATIVE_INFINITY;
    /**
     * Maximum of z
     */
    protected double maxZ = Double.NEGATIVE_INFINITY;

    /**
     * Calculate the distance between two geometries
     * according the middle of their boxes
     *
     * @param geoI the geo i
     * @param geoJ the geo j
     * @return The distance between geoI to geoJ
     */
    public static double distance(Intersectable geoI, Intersectable geoJ) {
        double midX, midY, midZ;
        midX = (geoI.minX + geoI.maxX) / 2d;
        midY = (geoI.minY + geoI.maxY) / 2d;
        midZ = (geoI.minZ + geoI.maxZ) / 2d;
        Point pGeoI = new Point(midX, midY, midZ);

        midX = (geoJ.minX + geoJ.maxX) / 2d;
        midY = (geoJ.minY + geoJ.maxY) / 2d;
        midZ = (geoJ.minZ + geoJ.maxZ) / 2d;
        Point pGeoJ = new Point(midX, midY, midZ);

        return pGeoI.dist(pGeoJ);
    }



    /**
     * check if the ray intersects with the box
     *
     * @param ray the ray
     * @return true or false
     */
    public Boolean checkIntersectionWithBox(Ray ray) {
        Vector rayVector = ray.getDir();
        Point rayPoint = ray.getP0();
        double pCX = rayPoint.getX();
        double pCY = rayPoint.getY();
        double pCZ = rayPoint.getZ();

        double pVX = rayVector.getX();
        double pVY = rayVector.getY();
        double pVZ = rayVector.getZ();

        double tXmin = (minX - pCX) / pVX;
        double tXmax = (maxX - pCX) / pVX;
        if (tXmin > tXmax) {
            double temp = tXmin;
            tXmin = tXmax;
            tXmax = temp;
        }

        double tYmin = (minY - pCY) / pVY;
        double tYmax = (maxY - pCY) / pVY;
        if (tYmin > tYmax) {
            double temp = tYmin;
            tYmin = tYmax;
            tYmax = temp;
        }
        if ((tXmin > tYmax) || (tYmin > tXmax))
            return false;

        if (tYmin > tXmin)
            tXmin = tYmin;

        if (tYmax < tXmax)
            tXmax = tYmax;


        double tZmin = (minZ - pCZ) / pVZ;
        double tZmax = (maxZ - pCZ) / pVZ;

        if (tZmin > tZmax) {
            double temp = tZmin;
            tZmin = tZmax;
            tZmax = temp;
        }
        return ((tXmin <= tZmax) && (tZmin <= tXmax));
    }

    /**
     * creating a box to the objects
     */
    protected abstract void createBox();

    /**
     * FindGeoIntersections with BVH.
     *
     * @param ray the ray
     * @return the list
     */
    public List<GeoPoint> findGeoIntersectionsBVH(Ray ray) {

        return checkIntersectionWithBox(ray) == null ? null : findGeoIntersections(ray);
        
    }

    /**
     * FindGeoIntersections with BVH.
     *
     * @param ray the ray
     * @return the list
     */
    public List<GeoPoint> findGeoIntersectionsBVH(Ray ray , double maxDistance) {

        return checkIntersectionWithBox(ray) == null ? null : findGeoIntersections(ray , maxDistance);
        
    }

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
