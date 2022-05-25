package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * PointLight class represents a light source who lights equally all over
 */
public class PointLight extends Light implements LightSource {
    private final Point position;

    private double kC = 1; // constant factor
    private double kL = 0; // linear factor
    private double kQ = 0; // quadrat factor

    /**
     * PointLight ctor
     * 
     * @param color color intensity
     * @param posit position
     */
    public PointLight(Color color, Point posit) {
        super(color);
        position = posit;
    }

    @Override
    public Color getIntensity(Point p) {
        double d2 = position.distSq(p);
        double attenuation = 1d / (kC + kL * Math.sqrt(d2) + kQ * d2);
        return intensity.scale(attenuation);
    }

    @Override
    public Vector getL(Point p) {
        return p.sub(position).normalize();
    }

    /**
     * setKc sets the constant parameter
     * 
     * @param c kC
     * @return current PointLight
     */
    public PointLight setKc(double c) {
        kC = c;

        return this;
    }

    /**
     * setKc sets the linear parameter
     * 
     * @param l kL
     * @return current PointLight
     */
    public PointLight setKl(double l) {
        kL = l;

        return this;
    }

    /**
     * setKc sets the quadrat parameter
     * 
     * @param q kQ
     * @return current PointLight
     */
    public PointLight setKq(double q) {
        kQ = q;

        return this;
    }

    @Override
    public double getDistance(Point point) {
        return position.dist(point);
    }
}
