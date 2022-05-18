package lighting;

import primitives.*;

/**
 * SpotLight class represents a light source who has direction
 */
public class SpotLight extends PointLight {

    private final Vector direction;
    private double beam=1;

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
        Color inten = super.getIntensity(p);
        //return inten.scale(Math.max(0, cosTetha));
        double factor=Math.max(0, cosTetha);
        if(beam!=1){
            factor=Math.pow(factor,beam);
        }
        return inten.scale(factor);
    }

    /**
     * set narrow beam
     * 
     * @param b narrow beam size
     * @return current light
     */
    public SpotLight setNarrowBeam(double b){
        beam=b;
        return this;
    }

}
