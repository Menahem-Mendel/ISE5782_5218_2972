package lighting;

import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * DirectionalLight class describes a direction light
 */
public class DirectionalLight extends Light implements LightSource {

    private final Vector direction; // vctor direction

    /**
     * ctor DirectionalLight
     * 
     * @param color color
     * @param dir   direction
     */
    public DirectionalLight(Color color, Vector dir) {
        super(color);
        direction = dir.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return intensity;
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public Point getPosition() {
        // has no position
        return null;
    }

    @Override
    public LightSource setRadius(double r) {
        // has no radius 
        return null;
    }

    @Override
    public double getRadius() {
        // has no radius
        return 0;
    }

    @Override
    public Vector getDirection() {
        // has no direction
        return direction;
    }

    @Override
    public LightSource setPoints(List<Point> p) {
        // has no list of points
        return null;
    }

    @Override
    public List<Point> getPoints() {
        // has no list of points
        return null;
    }

}
