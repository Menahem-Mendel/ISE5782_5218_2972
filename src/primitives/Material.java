package primitives;

/**
 * Material class , material the element
 */
public class Material {

    public int nShininess = 0; // shininess
    public Double3 kD = Double3.ZERO; // for diffuse
    public Double3 kS = Double3.ZERO; // for specular
    public Double3 kT = Double3.ZERO; // for transparency
    public Double3 kR = Double3.ZERO; // for reflection

    /**
     * setKd set diffuse
     * 
     * @param d double
     * @return current material
     */
    public Material setKd(Double3 d) {
        kD = d;

        return this;
    }

    /**
     * setKs set specular
     * 
     * @param s double
     * @return current material
     */
    public Material setKs(Double3 s) {
        kS = s;

        return this;
    }

    /**
     * setShininess set shininess
     * 
     * @param n int
     * @return current material
     */
    public Material setShininess(int n) {
        nShininess = n;

        return this;
    }

    /**
     * setKt set transparency
     * 
     * @param t double
     * @return current material
     */
    public Material setKt(Double3 t) {
        kT = t;

        return this;
    }

    /**
     * setKr set reflection
     * 
     * @param r double
     * @return current material
     */
    public Material setKr(Double3 r) {
        kR = r;

        return this;
    }

}
