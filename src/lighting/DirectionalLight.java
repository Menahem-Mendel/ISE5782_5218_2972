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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LightSource setRadius(double r) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getRadius() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Vector getDirection() {
        // TODO Auto-generated method stub
        return direction;
    }

    @Override
    public LightSource setPoints(List<Point> p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Point> getPoints() {
        // TODO Auto-generated method stub
        return null;
    }

}
