package primitives;

public class Material {

    public double kD = 0;
    public double kS = 0 ;
    public int nShininess = 0;

    public Material setKd(double d){
        kD=d;
        return this;
    }

    public Material setKs(double s){
        kS=s;
        return this;
    }

    public Material setShininess(int n){
        nShininess =n;
        return this;
    }

    
}
