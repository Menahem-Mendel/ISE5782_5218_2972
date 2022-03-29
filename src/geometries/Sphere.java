package geometries;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Point> findIntersections(Ray ray) {
        double t1, t2; // distances from p0 to the crossed points
        double t_m; // distance from p0 to the center of the chord
        double t_h; // distance from crossed points to the chord
        double d; // distance from the center of the sphere to the chord
        Point p0 = ray.getP0(); // head point of the ray
        Vector dir = ray.getDir(); // vector direction of the ray
        Vector u; // vector from p0 to the center of the sphere

        Point p1, p2; // intersection points
        p1 = p2 = null;

        // if the ray starts at the center add epsilon
        if (!center.equals(p0)) {
            u = center.sub(p0);
            t_m = dir.dot(u);
            double temp = Util.alignZero(u.lengthSquared() - t_m * t_m);
            d = (temp != 0) ? Math.sqrt(temp) : temp;

            // there are no intersections
            if (d >= radius)
                return null;

            t_h = Math.sqrt(radius * radius - d * d);

            t1 = t_m + t_h;
            t2 = t_m - t_h;
        } else {
            t1 = radius;
            t2 = -1; // because it's negative value we will not compute it
        }

        // p1 = p0 + direction * t1
        if (!Util.isZero(t1) && t1 > 0)
            p1 = p0.add(dir.scale(t1));

        // p2 = p0 + direction * t2
        if (!Util.isZero(t2) && t2 > 0)
            p2 = p0.add(dir.scale(t2));

        // if it is no intersections points
        if (p1 == null && p2 == null)
            return null;

        List<Point> ret = new ArrayList<Point>();
        if (p1 != null)
            ret.add(p1);
        if (p2 != null)
            ret.add(p2);

        return (ret.size() > 0) ? ret : null;
    }
}
