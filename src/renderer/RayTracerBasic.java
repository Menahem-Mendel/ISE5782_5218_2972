package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * RayTracerBasic it's a basic implementation of the RayTracerBase class
 * 
 */
public class RayTracerBasic extends RayTracerBase {

	/**
	 * RayTracerBasic build ctor
	 * 
	 * @param sc scene
	 */
	public RayTracerBasic(Scene sc) {
		super(sc);
	}

	@Override
	public Color traceRay(Ray r) {
		var intersections = scene.geometries.findIntersections(r);

		return intersections == null ? scene.background : calcColor(r.findClosestPoint(intersections));
	}

	/**
	 * calculates color of a given point
	 * 
	 * @param p intersection point to color
	 * @return Color of the point
	 */
	private Color calcColor(Point p) {
		return scene.ambientLight.getIntensity();
	}
}
