package geometries;

import java.util.List;

import primitives.*;

/**
 * Triangle class represents triangl in 3D space
 * 
 */
public class Triangle extends Polygon {

	/**
	 * Triangle represents triangle in 3D space
	 * 
	 * @param p1 vertex
	 * @param p2 vertex
	 * @param p3 vertex
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		List<Point> result = plane.findIntersections(ray);

		if (result == null)
			return null;

		Point P0 = ray.getP0();
		Vector v = ray.getDir();

		Point p1 = vertices.get(0);
		Point p2 = vertices.get(1);
		Point p3 = vertices.get(2);

		Vector v1 = p1.sub(P0);
		Vector v2 = p2.sub(P0);
		Vector v3 = p3.sub(P0);

		Vector n1 = v1.cross(v2);
		Vector n2 = v2.cross(v3);
		Vector n3 = v3.cross(v1);

		double s1 = v.dot(n1);
		double s2 = v.dot(n2);
		double s3 = v.dot(n3);

		if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0))
			return result;

		return null;
	}

}
