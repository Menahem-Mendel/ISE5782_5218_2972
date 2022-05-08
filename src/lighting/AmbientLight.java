package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class
 * 
 */
public class AmbientLight extends Light {

	/**
	 * AmbientLight build ctor
	 * 
	 * @param iA original color of the light
	 * @param kA attenuation factor of the original light
	 */
	public AmbientLight(Color iA, Double3 kA) {
		super(iA.scale(kA)); // Ip = Ka * Ia
	}

	/**
	 * AmbientLight empty build ctor sets the intensity to black
	 * 
	 */
	public AmbientLight() {
		super(Color.BLACK);
	}


}
