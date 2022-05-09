package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
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
                Color lightIntensity = lightSource.getIntensity(g.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShine, lightIntensity));
            }
        }
        return color;

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
