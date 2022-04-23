package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

public class Scene {
	public String name;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight = new AmbientLight();
	public Geometries geometries = new Geometries();

	public Scene(String n) {
		name = n;
		geometries = new Geometries();
	}

	public Scene setBackground(Color bg) {
		background = bg;

		return this;
	}

	public Scene setAmbientLight(AmbientLight al) {
		ambientLight = al;

		return this;
	}

	public Scene setGeometries(Geometries gs) {
		geometries = gs;

		return this;
	}
}
