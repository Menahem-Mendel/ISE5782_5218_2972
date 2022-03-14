package geometries;

import java.util.List;

import primitives.*;

/**
 * Intersectable describes every figure that can be intersection points with rays
 * 
 */
public interface Intersectable {

	/**
	 * findIntsersections method finds intersections with the ray
	 * 
	 * @param ray from the view point
	 * @return list of intersection points
	 */
	List<Point> findIntsersections(Ray ray);
}
