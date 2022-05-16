package lighting;

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

        direction = dir;
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }

}
