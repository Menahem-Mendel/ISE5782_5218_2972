package renderer;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

/**
 * RayTracerBasic it's a basic implementation of the RayTracerBase class
 */
public class RayTracerBasic extends RayTracerBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10; // for recursion
    private static final double MIN_CALC_COLOR_K = 0.001; // for recursion
    private static final Double3 INITIAL_K = new Double3(1d, 1d, 1d); // for recursion

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
        var intersections = scene.geometries.findGeoIntersections(r);

        return intersections == null ? scene.background
                : calcColor(r.findClosestGeoPoint(intersections), r);
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
        Color color = gp.geometry.getEmission().add(calcLocalEffects(gp, ray));

        return maxCalcColorLevel == 1 ? color
                : color.add(calcGlobalEffects(gp, ray.getDir(), maxCalcColorLevel, initialK));

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
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        Double3 kkr = material.kR.product(k);
        Double3 kkt = material.kT.product(k);

        if (kkr.higherThan(MIN_CALC_COLOR_K))
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);

        if (kkt.higherThan(MIN_CALC_COLOR_K))
            color = color.add(calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));

        return color;
    }

    /**
     * calcGlobalEffect call recursion
     * 
     * @param ray   ray
     * @param level depth level
     * @param kx    parameter for calcolor
     * @param kkx   the new parameter for calcolor
     * @return color
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);

        if (gp == null)
            return scene.background.scale(kx);

        Color temp = calcColor(gp, ray, level - 1, kkx);

        return temp.scale(kx);

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
        return new Ray(point, v.sub(n.scale(v.dot(n)).scale(2)), n);
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
     * @param r Ray
     * @return color
     */
    private Color calcLocalEffects(GeoPoint g, Ray r) {
        Vector v = r.getDir();
        Vector n = g.geometry.getNormal(g.point);
        double nv = Util.alignZero(n.dot(v));

        if (nv == 0)
            return Color.BLACK;

        Material m = g.geometry.getMaterial();
        int nShine = m.nShininess;
        Double3 kd = m.kD;
        Double3 ks = m.kS;
        Color color = Color.BLACK;

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(g.point);

            if (Util.alignZero(n.dot(l)) * nv > 0) {// transparency(g,lightSource,l,n,)
                Color lightIntensity = lightSource.getIntensity(g.point).scale(transparency(g, lightSource, l, n));

                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShine, lightIntensity));
            }
        }

        return color;
    }

    /**
     * unshaded checks if the current place is unshaded
     * 
     * @param ls light source
     * @param l  vector direction of light
     * @param n  vector normal of geometry
     * @param g  geoPoint
     * @return
     */
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
        return lightIntensity.scale(ks.scale(Math.pow(v.scale(-1).dot(l.sub(n.scale(l.dot(n) * 2))), nShine)));
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
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        double lightDistance = ls.getDistance(geoPoint.point);
        var intersections = scene.geometries.findGeoIntersections(lightRay);
        Double3 ktr = new Double3(1);
        if (intersections == null)
            return ktr;
        for (GeoPoint gp : intersections) {
            if (Util.alignZero(gp.point.dist(geoPoint.point) - lightDistance) <= 0) {
                ktr = ktr.product(gp.geometry.getMaterial().kT);
                if (ktr.lowerThan(MIN_CALC_COLOR_K))
                    return Double3.ZERO;
            }
        }
        return ktr;
    }
}
