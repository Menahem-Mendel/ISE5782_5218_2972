package geometries;

import primitives.*;

public class Plane implements Geometry {
	public Vector getNormal(Point p) {
		return new Vector(p.getXYZ());
	}
}
