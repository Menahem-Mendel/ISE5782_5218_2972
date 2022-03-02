package geometries;

import primitives.*;

public class Plane implements Geometry {

	private final Point q0;
	private final Vector normal;

	public Plane(Point p1, Point p2, Point p3) {
		Vector a = new Vector(p2.subtract(p1).getXYZ());
		Vector b = new Vector(p3.subtract(p1).getXYZ());
		Vector c = a.crossProduct(b);

		q0 = p1;
		normal = c.normalize();
	}

	public Plane(Point p, Vector n) {
		q0 = p;
		normal = n.normalize();
	}

	public Vector getNormal() {
		return normal;
	}

	public Vector getNormal(Point p) {
		return normal;
	}
}
