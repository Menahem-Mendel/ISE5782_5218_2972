package primitives;

/**
 * Material class , material the element
 */
public class Material {

    public Double3 kD = new Double3(0.0); // for diffuse
    public Double3 kS = new Double3(0.0); // for specular
    public int nShininess = 0; // shininess
    public Double3 kT = new Double3(0.0); // for transparency
    public Double3 kR = new Double3(0.0); // for reflection

    /**
     * setKd set kD
     * 
     * @param d double
     * @return current material
     */
    public Material setKd(Double3 d) {
        kD = d;
        return this;
    }

    /**
     * setKs set kS
     * 
     * @param s double
     * @return current material
     */
    public Material setKs(Double3 s) {
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
    public Material setKt(Double3 t) {
        kT = t;
        return this;
    }

    /**
     * setKr srt kR for reflection
     * 
     * @param r double
     * @return current material
     */
    public Material setKr(Double3 r) {
        kR = r;
        return this;
    }

}
