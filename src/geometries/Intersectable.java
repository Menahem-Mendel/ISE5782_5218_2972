package geometries;

import java.util.*;

import primitives.*;

/**
 * Intersectable interface describe the intersections with Geomteries
 * 
 */
public interface Intersectable {

    /**
     * findIntersections finds the intersection points
     * 
     * @param ray that goes through geometries
     * @return List of Points the ray cross the geometry
     */
    public List<Point> findIntersections(Ray ray);

}
