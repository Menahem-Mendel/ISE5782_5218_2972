package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class
 * 
 */
public class AmbientLight {
	private final Color intensity;

	/**
	 * AmbientLight build ctor
	 * 
	 * @param iA original color of the light
	 * @param kA attenuation factor of the original light
	 */
	public AmbientLight(Color iA, Double3 kA) {
		intensity = iA.scale(kA); // Ip = Ka * Ia
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
