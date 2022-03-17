package geometries;
import java.util.*;

import primitives.*;


/**
 * Intersectable interface describe the intersections with Geomteries
 * 
 */
public interface Intersectable  {

    /**
     * 
     * @param ray that goes through geometries
     * @return List of Points the ray touch the geometry
     */
    public List<Point> findIntsersections(Ray ray);
    
}
