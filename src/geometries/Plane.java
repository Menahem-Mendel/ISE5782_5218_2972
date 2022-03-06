package geometries;

/**
 * plane defines by a point and a vertical vector
 */
import primitives.*;

public class Plane implements Geometry {

	private final Point q0;
	private final Vector normal;

	/**
	 * Constructor for a plane by 3 points
	 * @param p1 first point
	 * @param p2 second point
	 * @param p3 third point
	 */
	public Plane(Point p1, Point p2, Point p3) {
		Vector a = new Vector(p2.subtract(p1).getXYZ());
		Vector b = new Vector(p3.subtract(p1).getXYZ());
		Vector c = a.crossProduct(b);

		q0 = p1;
		normal = c.normalize();
	}

	/**
	 * Contructor for a plane by a vector and a point
	 * @param p point in space
	 * @param n vectour normal of the plane
	 */
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
