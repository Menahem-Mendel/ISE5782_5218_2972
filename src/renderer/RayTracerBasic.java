package renderer;

import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * RayTracerBasic it's a basic implementation of the RayTracerBase class
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
		var intersections = scene.geometries.findGeoIntersectionsHelper(r);

		return intersections == null ? scene.background
				: calcColor(r.findClosestGeoPoint(intersections));
	}

	/**
	 * calculates color of a given point
	 * 
	 * @param p intersection point to color
	 * @return Color of the point
	 */
	private Color calcColor(GeoPoint p) {
		// !!! wtf is point color
		return scene.ambientLight.getIntensity().add(p.geometry.getEmission()).add();
	}
}
