package lighting;

import primitives.*;
import static primitives.Util.*;

/**
 * SpotLight class represents a light source who has direction
 */
public class SpotLight extends PointLight {

    private final Vector direction;
    private double beam = 1;

    /**
     * bulid ctor for SpotLight
     * 
     * @param color color
     * @param posit position
     * @param dir   direction
     */
    public SpotLight(Color color, Point posit, Vector dir) {
        super(color, posit);
        direction = dir.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        double cosTetha = direction.dot(getL(p));
        if (alignZero(cosTetha) <= 0)
            return Color.BLACK;
        // return inten.scale(Math.max(0, cosTetha));
        double factor = beam == 1 ? cosTetha : Math.pow(cosTetha, beam);
        return super.getIntensity(p).scale(factor);
    }

    /**
     * set narrow beam
     * 
     * @param b narrow beam size
     * @return current light
     */
    public SpotLight setNarrowBeam(double b) {
        beam = b;
        return this;
    }

}
