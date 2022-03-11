package geometries;

import primitives.*;

/**
 * Tube class represents infinite cylinder in 3D space
 * 
 */
public class Tube implements Geometry {

	private final Ray axisRay;
	private final double radius;

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
	public  Vector getNormal(Point p) {
		
		Vector unit = getAxisRay().getDir().normalize();  //unit vector the same direction as the ray
		double t =   unit.dot(p.sub(getAxisRay().getP0())); //projection on axisRay
		if(Util.isZero(t)){
			return p.sub(new Point(0,0,0));
		}
		Point pointOnRay = getAxisRay().getP0().add(unit.scale(t)); // pointOnRay- head of the normal vector


		return p.sub(pointOnRay).normalize(); 
	}
}
