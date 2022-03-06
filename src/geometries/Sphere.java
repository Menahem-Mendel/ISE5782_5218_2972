package geometries;

/**
 * Sphere defines by a point and size of the radius
 */
import primitives.*;
public class Sphere implements Geometry {

    private final Point center;
	private final double radius;

    public Sphere(Point p , double r){
        center= p;
        radius = r;
    }

    public Point getCenter(){
        return center;
    }

    public double getRadius(){
        return radius;
    }

    @Override
    public Vector getNormal(Point p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }


}
