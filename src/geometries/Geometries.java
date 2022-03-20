package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;

/**
 * Geometries can hold few different geometries
 */
public class Geometries implements  Intersectable{

    private List<Intersectable> list;

    /**
     * default ctor
     */
    public Geometries(){
        
        //LinkedList because adding is O(1)
        list = new LinkedList<Intersectable>();
    }

    /**
     * ctor
     * @param geometries list who contains geometries
     */
    public Geometries(Intersectable... geometries){

        list = new LinkedList<Intersectable>();

        for (int i = 0; i < geometries.length; i++){
            list.add((geometries[i]));
        }

    }

    /**
     * adding more geometries to the list
     * @param geometries list who contains geometries
     */
    public void add(Intersectable... geometries){

        for (int i = 0; i < geometries.length; i++){
            list.add((geometries[i]));
        }

    }

    @Override
    public List<Point> findIntersections (Ray ray) {
        return null;
    }

    
    
}