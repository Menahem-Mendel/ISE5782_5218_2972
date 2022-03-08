package primitives;

/**
 * Ray represents directional vector which starts from the starting point
 *
 */
public class Ray {
	private final Point p0;
	private final Vector dir;

	/**
	 * Ray build ctor
	 * 
	 * @param p starting point
	 * @param v directional vector
	 */
	public Ray(Point p, Vector v) {
		if (Util.isZero(v.length()))
			throw new IllegalArgumentException(Errors.ZERO_VEC);

		dir = v.normalize();
		p0 = p;
	}

	/**
	 * getP0 get starting point
	 * 
	 * @return starting point
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * getDir get directional vector
	 * 
	 * @return vector direction
	 */
	public Vector getDir() {
		return dir;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return p0.equals(other.p0) && dir.equals(other.dir);
	}

	@Override
	public String toString() {
		return "->" + String.format("%s, %s", p0.toString(), dir.toString());
	}
}
