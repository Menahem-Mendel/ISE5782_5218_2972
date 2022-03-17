package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;

public class Geometries implements  Intersectable{

    private List<Intersectable> list;

    public Geometries(){

    }

    public Geometries(Intersectable... geometries){


    }

    public void add(Intersectable... geometries){


    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }

    
    
}
