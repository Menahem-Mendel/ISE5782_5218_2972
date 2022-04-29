package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * class Scene contains tetailes about our scene 
 */
public class Scene {
	public String name;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight = new AmbientLight();
	public Geometries geometries = new Geometries();

	/**
	 * ctor, build an empty Geometries
	 * @param n name of the scene
	 */
	public Scene(String n) {
		name = n;
		geometries = new Geometries();
	}

	/**
	 * set the background color
	 * 
	 * @param bg background color
	 * @return Scene object
	 */
	public Scene setBackground(Color bg) {
		background = bg;

		return this;
	}

	/**
	 * set the ambient light
	 * 
	 * @param al ambient light
	 * @return Scene object
	 */
	public Scene setAmbientLight(AmbientLight al) {
		ambientLight = al;

		return this;
	}

	/**
	 * set the Geometries
	 * 
	 * @param gs Geometries
	 * @return Scene object
	 */
	public Scene setGeometries(Geometries gs) {
		geometries = gs;

		return this;
	}
}
