package lighting;

import primitives.Color;

/**
 * Light Class, represnt the light
 */
public abstract class Light {

    protected final Color intensity; // color

    /**
     * ctor with parameter of the color
     * 
     * @param color to initialize
     */
    protected Light(Color color) {
        intensity = color;
    }

    /**
     * getIntensity returns the intensity of the light
     * 
     * @return intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }

}
