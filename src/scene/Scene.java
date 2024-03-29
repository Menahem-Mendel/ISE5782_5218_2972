package scene;

import java.util.LinkedList;
import java.util.List;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

/**
 * class Scene contains tetailes about our scene
 */
public class Scene {
	/**
	 * name of the scene
	 */
	public final String name; 
	/**
	 * background color of the scene
	 */
	public Color background = Color.BLACK; 
	/**
	 *  ambient light in the scene
	 */
	public AmbientLight ambientLight = new AmbientLight(); 
	/**
	 *  geometries on the scene
	 */
	public Geometries geometries = new Geometries(); 
	/**
	 * list of lights
	 */
	public List<LightSource> lights = new LinkedList<>(); 

	/**
	 * ctor, build an empty Geometries
	 * 
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
