package lighting;

import primitives.*;
import static primitives.Util.*;

import java.util.List;

/**
 * SpotLight class represents a light source who has direction
 */
public class SpotLight extends PointLight {

    private final Vector direction; //direction of the light
    private double beam = 1; //for narrow beam 
    private List<Point> randPoints = null; //for super sampling, points on the spot light

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

    @Override
    public Vector getDirection(){
        return direction;
    }

    @Override
    public SpotLight setPoints(List<Point> p){
        randPoints=p;
        return this;
    }

    @Override
    public List<Point> getPoints(){
        return randPoints;
    }

}
