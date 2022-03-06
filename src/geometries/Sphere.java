package geometries;

/**
 * Sphere defines by a point and size of the radius
 */
import primitives.*;

/**
 * Sphere class represents a sphere in two-dimensional space
 * 
 */
public class Sphere implements Geometry {

    private final Point center;
    private final double radius;

    /**
     * Sphere build ctor
     * 
     * @param p center point
     * @param r radius
     */
    public Sphere(Point p, double r) {
        center = p;
        radius = r;
    }

    /**
     * getCenter
     * 
     * @return center point
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getRadius
     * 
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
