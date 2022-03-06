package geometries;

/**
 * Tube defines by a Ray and radius
 */
import primitives.*;
public class Tube implements Geometry {

    private final Ray axisRay;
	private final double radius;

    public Tube(Ray ray , double r){
        axisRay=ray;
        radius=r;
    }

    public Ray getAxisRay(){
        return axisRay;
    }

    public double getRadius(){
        return radius;
    }

    
    @Override
    public Vector getNormal(Point p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
