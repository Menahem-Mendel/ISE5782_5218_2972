package geometries;

import primitives.*;

/**
 * Geometry interface describes every geometry in multi-dimensional space
 * 
 */
public interface Geometry {
	/**
	 * getNormal returns normal vector of a particular point on the figure
	 * 
	 * @param p 3D point
	 * @return vector normal
	 */
	Vector getNormal(Point p);
}
