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

  
     
}
