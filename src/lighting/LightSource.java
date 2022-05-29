package lighting;

import java.util.List;

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

    /**
     * get the position of the center of the light
     * @return Point position
     */
    public Point getPosition();

    /**
     * set size of radius of light source
     * @param r radius
     * @return current PointLight
     */
    public LightSource setRadius(double r);

    /**
     * return radius of the current light sorce
     * @return radius
     */
    public double getRadius();

    /**
     * getDirection
     * @return vector direction 
     */
    public Vector getDirection();

    /**
     * setPoints for spot light
     * @param p list of Point
     * @return this
     */
    public LightSource setPoints(List<Point> p);

    /**
     * getPoints of the spot light
     * @return list of points
     */
    public List<Point> getPoints();

}
