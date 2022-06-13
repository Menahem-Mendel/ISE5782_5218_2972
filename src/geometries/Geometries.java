package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Ray;

/**
 * Geometries can hold few different geometries
 */
public class Geometries extends Intersectable {

    private List<Intersectable> list = new LinkedList<>();

    /**
     * Geometries build ctor
     * 
     * @param geometries list who contains geometries
     */
    public Geometries(Intersectable... geometries) {
        if (geometries.length > 0)
            add(geometries);
    }

    /**
     * add geometries to the list
     * 
     * @param geometries list containing geometries
     */
    public void add(Intersectable... geometries) {
        for(Intersectable g : geometries){
            g.createBox();;
            this.createBox(g);
            list.add(g);
        }
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> ret = null;

        for (var g : list) {
            var elems = g.findGeoIntersections(ray, maxDistance);

            if (elems == null)
                continue;

            if (ret == null)
                ret = new LinkedList<>(elems);
            else
                ret.addAll(elems);
        }

        return ret;
    }

    @Override
    public void createBox() {
        for(Intersectable l : list){
            l.createBox();
            this.createBox(l);
        }
    }

    /**
     * Create new size of box cuz the intersectable
     *
     * @param inter intersectable
     */
    void createBox(Intersectable inter) {
        this.minX = Math.min(inter.minX, this.minX);
        this.maxX = Math.max(inter.maxX, this.maxX);
        this.minY = Math.min(inter.minY, this.minY);
        this.maxY = Math.max(inter.maxY, this.maxY);
        this.minZ = Math.min(inter.minZ, this.minZ);
        this.maxZ = Math.max(inter.maxZ, this.maxZ);
    }

   
    /**
     * buildBvhTree creating a Tree of geometries based on their distances 
     */
    public void buildBvhTree() {
        List<Intersectable> infiniteGeometries = new LinkedList<>();//a plane list
        for (Intersectable geo : list) {
            if (geo instanceof Plane)
                infiniteGeometries.add(geo);
        }
        list.removeAll(infiniteGeometries);
        double distance = 0;
        Intersectable left = null;
        Intersectable right = null;
        while (list.size() > 1) {
            double best = Double.POSITIVE_INFINITY;
            Intersectable geoI = list.get(0);
            for (Intersectable geoJ : list) {
                if (geoI != geoJ && (distance = distance(geoI, geoJ)) < best) {
                    best = distance;
                    left = geoI;
                    right = geoJ;
                }
            }
            Geometries tempGeometries = new Geometries(left, right);
            list.remove(left);
            list.remove(right);
            list.add(tempGeometries);
        }
        list.addAll(infiniteGeometries);
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsBVH(Ray ray) {
        List<GeoPoint> intersections = null;
        List<GeoPoint> tempIntersection = null;
        for (Intersectable geo : list) {
            if (geo.checkIntersectionWithBox(ray)) {
                tempIntersection = geo.findGeoIntersectionsBVH(ray);
            }
            if (tempIntersection != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(tempIntersection);
            }
        }
        return intersections;
    }
  
     
}
