package geometries;

import primitives.*;

/**
 * Geometry interface describes every geometry in multi-dimensional space
 * 
 */
public interface Geometry {
	Vector getNormal(Point p);
}
