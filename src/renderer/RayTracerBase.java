package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * RayTracerBase traces the path of the ray
 */
public abstract class RayTracerBase {
	protected Scene scene;

	/**
	 * RayTracerBase build ctor
	 * 
	 * @param sc scene
	 */
	public RayTracerBase(Scene sc) {
		scene = sc;
	}

	/**
	 * traceRay returns color of ray trace
	 * 
	 * @param r ray
	 * @return color
	 */
	public abstract Color traceRay(Ray r);
}
