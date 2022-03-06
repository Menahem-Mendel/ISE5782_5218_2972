package primitives;

/**
 * Ray defines All points on the line that are on one side of the point.
 *
 */
public class Ray {
	private Point p0;
	private Vector dir;

	/**
	 * Ray build ctor
	 * 
	 * @param p
	 * @param v
	 */
	public Ray(Point p, Vector v) {
		dir = v.normalize();
		p0 = p;
	}

	/**
	 * getP0
	 * 
	 * @return starting point
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * getDir
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
		return p0.toString() + dir.toString();
	}
}
