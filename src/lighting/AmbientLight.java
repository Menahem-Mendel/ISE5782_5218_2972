package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class
 * 
 */
public class AmbientLight {
	private Color intensity;

	/**
	 * AmbientLight build ctor
	 * 
	 * @param Ia original color of the light
	 * @param Ka attenuation factor of the original light
	 */
	public AmbientLight(Color Ia, Double3 Ka) {
		intensity = Ia.scale(Ka); // Ip = Ka * Ia
	}

	/**
	 * AmbientLight empty build ctor sets the intensity to black
	 * 
	 */
	public AmbientLight() {
		intensity = Color.BLACK;
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
