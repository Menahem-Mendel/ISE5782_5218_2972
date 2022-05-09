package lighting;

import primitives.*;

/**
 * interface for all light sources
 */
public interface LightSource {

    /**
     * 
     * @param p Point
     * @return color Intensity
     */
    public Color getIntensity(Point p);

    /**
     * 
     * @param p Point
     * @return direction of light
     */
    public Vector getL(Point p);

}
