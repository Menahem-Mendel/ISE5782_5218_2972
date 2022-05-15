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

    private static final int MAX_CALC_COLOR_LEVEL = 10;   //for Recursion
    private static final double MIN_CALC_COLOR_K = 0.001;  // for Recursion

    /**
     * moving the intersect point with dist of delta
     */
    private static final double EPS = 0.1;

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
    private Color calcColor(GeoPoint p, Ray ray) {
        return scene.ambientLight.getIntensity().add(p.geometry.getEmission()).add(calcLocalEffeects(p, ray));
    }

    /**
     * calcLocalEffeects calculates color with phong model
     * 
     * @param g GeoPoint
     * @param r Ray
     * @return color
     */
    private Color calcLocalEffeects(GeoPoint g, Ray r) {
        Vector v = r.getDir();
        Vector n = g.geometry.getNormal(g.point);
        double nv = Util.alignZero(n.dot(v));
        if (nv == 0) {
            return Color.BLACK;
        }
        Material m = g.geometry.getMaterial();
        int nShine = m.nShininess;
        double kd = m.kD;
        double ks = m.kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(g.point);
            double nl = Util.alignZero(n.dot(l));
            if (nl * nv > 0) {
                if (unshaded(lightSource, l, n, g, nv)) {
                    Color lightIntensity = lightSource.getIntensity(g.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShine, lightIntensity));
                }
            }
        }
        return color;

    }

    /**
     * unshaded checks if the current place is un shaded
     * 
     * @param lightSource lightSource
     * @param l           vector direction of light
     * @param n           vector normal of geometry
     * @param g           geoPoint
     * @return
     */
    private boolean unshaded(LightSource lightSource, Vector l, Vector n, GeoPoint g, double nv) {

        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nv < 0 ? EPS : -EPS);
        Point point = g.point.add(epsVector); // !!! MAYBE BREAK LAW OF DEMETER -> SOLUTION CREATING A NEW RAY CTOR
        Ray lightRay = new Ray(point, lightDirection);
        double dist = lightSource.getDistance(point);

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, dist);
        return intersections == null;
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
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShine,
            Color lightIntensity) {
        Vector r = l.sub(n.scale(l.dot(n) * 2));
        double vrMinus = v.scale(-1).dot(r);
        double vrn = Math.pow(vrMinus, nShine);
        return lightIntensity.scale(ks * vrn);
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
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        double ln = Math.abs(l.dot(n));
        return lightIntensity.scale(kd * ln);
    }

}
