package lighting;

import primitives.*;

/**
 * interface for all light sources
 */
public interface LightSource {

    /**
     * get color intensity
     * 
     * @param p Point
     * @return color Intensity
     */
    public Color getIntensity(Point p);

    /**
     * getL get direction of light 
     * 
     * @param p Point
     * @return direction of light
     */
    public Vector getL(Point p);

    /**
     * get distance between point and light source
     * 
     * @param point point
     * @return distance
     */
    double getDistance(Point point);

}
