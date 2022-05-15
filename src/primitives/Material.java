package primitives;

/**
 * Material class , material the element
 */
public class Material {

    public double kD = 0; // for diffuse
    public double kS = 0; // for specular
    public int nShininess = 0; // shininess
    public double kT = 0; // for transparency
    public double kR = 0; // for reflection

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

    /**
     * setKt set kT for transparency
     * 
     * @param t double
     * @return current material
     */
    public Material setKt(double t) {
        kT = t;
        return this;
    }

    /**
     * setKr srt kR for reflection
     * 
     * @param r double
     * @return current material
     */
    public Material setKr(double r) {
        kR = r;
        return this;
    }

}
