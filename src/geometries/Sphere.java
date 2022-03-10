package geometries;

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
     * getCenter returns the center point of the sphere
     * 
     * @return center point
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getRadius returns the radius of the sphere
     * 
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point p) {
        return p.sub(center).normalize();
    }
}
