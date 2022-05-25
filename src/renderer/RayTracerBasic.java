package renderer;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import lighting.*;
import primitives.*;
import static primitives.Util.*;
import scene.Scene;

/**
 * RayTracerBasic it's a basic implementation of the RayTracerBase class
 */
public class RayTracerBasic extends RayTracerBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10; // for recursion
    private static final double MIN_CALC_COLOR_K = 0.001; // for recursion
    private static final Double3 INITIAL_K = Double3.ONE; // for recursion

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
        var intersection = findClosestIntersection(r);
        return intersection == null ? scene.background : calcColor(intersection, r);
    }

    /**
     * calculates color of a given point
     * 
     * @param p   intersection point to color
     * @param ray a given ray
     * @return Color of the point
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    /**
     * calculate color recursion
     * 
     * @param gp                geo point
     * @param ray               ray
     * @param maxCalcColorLevel int max for recursion
     * @param initialK          double max for recursion
     * @return color
     */
    private Color calcColor(GeoPoint gp, Ray ray, int maxCalcColorLevel, Double3 initialK) {
        Vector dir = ray.getDir();
        Color color = calcLocalEffects(gp, dir);

        return maxCalcColorLevel == 1 ? color : color.add(calcGlobalEffects(gp, dir, maxCalcColorLevel, initialK));

    }

    /**
     * calcGlobalEffect calculate global effects
     * 
     * @param gp    geo pint
     * @param v     vector
     * @param level int depth level
     * @param k     INITIAL_K for recursion
     * @return color
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, Double3 k) {
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, k, material.kR)
                .add(calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, k, material.kT));
    }

    /**
     * calcGlobalEffect call recursion
     * 
     * @param ray   ray
     * @param level depth level
     * @param k     recursion factor
     * @param kx    transparency or reflection factor
     * @return color
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K))
            return Color.BLACK;

        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    /**
     * construct a new ray for refraction
     * 
     * @param point for ray's p0
     * @param v     vector direction
     * @param n     vector normal
     * @return ray
     */
    private Ray constructRefractedRay(Point point, Vector v, Vector n) {
        return new Ray(point, v, n);
    }

    /**
     * construct a new ray for reflected
     * 
     * @param point for ray's p0
     * @param v     vector direction
     * @param n     vector normal
     * @return ray
     */
    private Ray constructReflectedRay(Point point, Vector v, Vector n) {
        return new Ray(point, v.sub(n.scale(v.dot(n) * 2d)), n);
    }

    /**
     * find closest intersection of a given ray
     * 
     * @param ray a given ray
     * @return GeoPoint the closest one
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> gpList = scene.geometries.findGeoIntersections(ray);

        return ray.findClosestGeoPoint(gpList);
    }

    /**
     * calcLocalEffects calculates color with phong model
     * 
     * @param g GeoPoint
     * @param v ray direction
     * @return color
     */
    private Color calcLocalEffects(GeoPoint g, Vector v) {
        Vector n = g.geometry.getNormal(g.point);
        double nv = alignZero(n.dot(v));
        if (nv == 0)
            return Color.BLACK;

        Material m = g.geometry.getMaterial();
        Color color = g.geometry.getEmission();

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(g.point);

            if (alignZero(n.dot(l)) * nv > 0) {
                Double3 tr = transparency(g, lightSource, l, n);
                if (!tr.lowerThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(g.point).scale(tr);
                    color = color.add(calcDiffusive(m.kD, l, n, lightIntensity),
                            calcSpecular(m.kS, l, n, v, m.nShininess, lightIntensity));
                }
            }
        }

        return color;
    }

    /**
     * unshaded checks if the current place is unshaded
     * 
     * @deprecated Use transparency(...) method instead of this one
     * @param ls light source
     * @param l  vector direction of light
     * @param n  vector normal of geometry
     * @param g  geoPoint
     * @return false is shaded, true otherwise
     */
    @SuppressWarnings("unused")
    @Deprecated
    private boolean unshaded(LightSource ls, Vector l, Vector n, GeoPoint gp, double nv) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(
                new Ray(gp.point, l.scale(-1), n), ls.getDistance(gp.point));

        if (intersections != null)
            for (GeoPoint intersection : intersections)
                if (intersection.geometry.getMaterial().kT == Double3.ZERO)
                    return false;

        return true;

    }

    /**
     * calcSpecular calculates specular light
     * 
     * @param ks             double describes material
     * @param l              vector direction of light
     * @param n              vector geometry normal
     * @param v              vector direction of the given ray
     * @param nShine         int describes nShininess
     * @param lightIntensity color current color
     * @return color result
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShine, Color lightIntensity) {
        double vr = v.dot(l.sub(n.scale(l.dot(n) * 2)));
        return alignZero(vr) >= 0 ? Color.BLACK : lightIntensity.scale(ks.scale(Math.pow(-vr, nShine)));
    }

    /**
     * calcDiffusive calculates diffusive light
     * 
     * @param kd             double describes material
     * @param l              vector direction of light
     * @param n              vector geometry normal
     * @param lightIntensity color current color
     * @return color result
     */
    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
        return lightIntensity.scale(kd.scale(Math.abs(l.dot(n))));
    }

    /**
     * transparency check transparency in a place
     * 
     * @param geoPoint goe point
     * @param ls       light source
     * @param l        direction light
     * @param n        normal of geo point
     * @return Double3
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n) {
        Double3 ktr = Double3.ONE;
        Ray lightRay = new Ray(geoPoint.point, l.scale(-1), n);
        var intersections = scene.geometries.findGeoIntersections(lightRay, ls.getDistance(geoPoint.point));
        if (intersections == null)
            return ktr;

        for (GeoPoint gp : intersections) {
            ktr = ktr.product(gp.geometry.getMaterial().kT);
            if (ktr.lowerThan(MIN_CALC_COLOR_K))
                return Double3.ZERO;
        }
        return ktr;
    }
}
