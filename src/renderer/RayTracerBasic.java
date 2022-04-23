package renderer;

import java.util.List;

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
		List<Point> intersections = scene.geometries.findIntersections(r);

		if (intersections == null)
			return scene.background;

		return calcColor(r.findClosestPoint(intersections));
	}

	private Color calcColor(Point p) {
		return scene.ambientLight.getIntensity();
	}
}
