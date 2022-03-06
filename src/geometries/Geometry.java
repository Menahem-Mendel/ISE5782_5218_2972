package geometries;

import primitives.*;
/**
 * interface for all Geometries with getnormal
 */
public interface Geometry {
	Vector getNormal(Point p);
}
