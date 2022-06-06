package geometries;

import java.util.List;

import primitives.*;

/**
 * Tube class represents infinite cylinder in 3D space
 */
public class Tube extends Geometry {

	protected final Ray axisRay;
	protected final double radius;

	/**
	 * Tube build ctor
	 * 
	 * @param ray of direction
	 * @param r   radius
	 */
	public Tube(Ray ray, double r) {
		axisRay = ray;
		radius = r;
	}

	/**
	 * getAxisRay returns axis ray
	 * 
	 * @return ray axis
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	/**
	 * getRadius returns radius of the tube base
	 * 
	 * @return radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point p) {
		return p.sub(axisRay.getPoint(axisRay.getDir().dot(p.sub(axisRay.getP0())))).normalize();
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		throw new UnsupportedOperationException("not yet implemented");
	}

	@Override
	public void createBox() {
		minX = Double.NEGATIVE_INFINITY;
		maxX = Double.POSITIVE_INFINITY;
		minY = Double.NEGATIVE_INFINITY;
		maxY = Double.POSITIVE_INFINITY;
		minZ = Double.NEGATIVE_INFINITY;
		maxZ = Double.POSITIVE_INFINITY;
	}
}
