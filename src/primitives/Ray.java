package primitives;

import java.util.List;

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
		dir = v.normalize();
		p0 = p;
	}

	/**
	 * getP0 returns starting point
	 * 
	 * @return starting point
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * getDir returns directional vector
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
		if (obj == null || !(obj instanceof Ray other))
			return false;
		return p0.equals(other.p0) && dir.equals(other.dir);
	}

	@Override
	public String toString() {
		return String.format("-> %s, %s", p0, dir);
	}

	/**
	 * getPoint get the point on the line (p0 + t*dir)
	 * 
	 * @param t dir scale
	 * @return point on the line
	 */
	public Point getPoint(double t) {
		return Util.isZero(t) ? p0 : p0.add(dir.scale(t));
	}

	/**
	 * findClosestPoint finds closest point to the ray's head
	 * 
	 * @param lst list of points
	 * @return closest point to the ray's head
	 */
	public Point findClosestPoint(List<Point> lst) {
		if (lst.isEmpty())
			return null;

		Point closest = lst.get(0);

		for (Point p : lst)
			if (closest.distSq(p0) > p.distSq(p0))
				closest = p;

		return closest;
	}
}
