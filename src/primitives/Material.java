package primitives;

/**
 * Material class , material the element
 */
public class Material {

    public double kD = 0;
    public double kS = 0;
    public int nShininess = 0;

    /**
     * setKd set kD
     * 
     * @param d double
     * @return current material
     */
    public Material setKd(double d) {
        kD = d;
        return this;
    }

    /**
     * setKs set kS
     * 
     * @param s double
     * @return current material
     */
    public Material setKs(double s) {
        kS = s;
        return this;
    }

    /**
     * setShininess set nShininess
     * 
     * @param n int
     * @return current material
     */
    public Material setShininess(int n) {
        nShininess = n;
        return this;
    }

}
