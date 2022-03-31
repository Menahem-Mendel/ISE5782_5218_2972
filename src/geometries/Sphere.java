package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Sphere class represents a sphere in two-dimensional space
 * 
 */
public class Sphere implements Geometry {

    private final Point center;
    private final double radius;
    private final double radius2;

    /**
     * Sphere build ctor
     * 
     * @param p center point
     * @param r radius
     */
    public Sphere(Point p, double r) {
        center = p;
        radius = r;
        radius2 = r * r;
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

    @Override
    public List<Point> findIntersections(Ray ray) {
        Vector u; // vector from p0 to the center of the sphere
        try {
            u = center.sub(ray.getP0());
        } catch (IllegalArgumentException ignore) {
            // the ray starts at the center
            return List.of(ray.getPoint(radius));
        }

        double tm = ray.getDir().dot(u);// distance from p0 to the center of the chord

        // squared distance from the center of the sphere to the chord
        double d2 = Util.alignZero(u.lengthSquared() - tm * tm);

        double th2 = radius2 - d2;// squared distance from crossed points to the chord
        // there are no intersections
        if (alignZero(th2) <= 0)
            return null;

        double th = Math.sqrt(th2); // distance from crossed points to the chord
        double t2 = alignZero(tm + th); // distance from p0 to the furhter point
        if (t2 <= 0)
            return null;

        double t1 = alignZero(tm - th); // distance from p0 to the nearer point
        return t1 <= 0 ? List.of(ray.getPoint(t2)) : List.of(ray.getPoint(t1), ray.getPoint(t2));
    }
}
