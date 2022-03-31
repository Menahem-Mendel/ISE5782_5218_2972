package geometries;

import java.util.*;

import primitives.*;

/**
 * Intersectable interface describe the intersections with Geomteries
 * variables + methods camelCase
 * variables: class (static), instance (object), local, parameters
 * variables - main word is a subject, methods - main word is a verb
 * types CamelCase
 * CONSTANT_NAMES
 * packagesnames
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
