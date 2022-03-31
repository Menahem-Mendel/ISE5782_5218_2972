package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

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

		Point p0 = ray.getP0();
		Vector v = ray.getDir();

		Vector v1 = vertices.get(0).sub(p0);
		Vector v2 = vertices.get(1).sub(p0);
		Vector n1 = v1.cross(v2);
		double s1 = alignZero(v.dot(n1));
		if (s1 == 0)
			return null;

		Vector v3 = vertices.get(2).sub(p0);
		Vector n2 = v2.cross(v3);
		double s2 = alignZero(v.dot(n2));
		if (s1 * s2 <= 0)
			return null;

		Vector n3 = v3.cross(v1);
		double s3 = alignZero(v.dot(n3));
		if (s1 * s3 <= 0)
			return null;

		return result;
	}

}
