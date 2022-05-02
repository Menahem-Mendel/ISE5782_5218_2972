package renderer;

import primitives.*;
import scene.Scene;

/**
 * Abstract class for all possible ray tacers
 */
public abstract class RayTracerBase {
	protected final Scene scene;

	/**
	 * Initializes a ray tracer with a given scene
	 * 
	 * @param sc scene to trace a ray through
	 */
	public RayTracerBase(Scene sc) {
		scene = sc;
	}

	/**
	 * traceRay returns color of ray trace
	 * 
	 * @param r ray to trace
	 * @return color of the ray
	 */
	public abstract Color traceRay(Ray r);
}
