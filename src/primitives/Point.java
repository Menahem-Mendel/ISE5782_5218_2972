package primitives;

/**
 * Point class is a single unit representing the dot in space
 * 
 */
public class Point {
	protected final Double3 xyz;

	/**
	 * Point build ctor
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param z z coordinate
	 */
	public Point(double x, double y, double z) {
		xyz = new Double3(x, y, z);
	}

	/**
	 * Point build ctor
	 * 
	 * @param d 3D point
	 */
	public Point(Double3 d) {
		xyz = d;
	}

	/**
	 * getXYZ
	 * 
	 * @return xyz
	 */
	public Double3 getXYZ() {
		return xyz;
	}

	/**
	 * add vector to a point
	 * 
	 * @param v vector
	 * @return vector of sum
	 */
	public Point add(Vector v) {
		return new Point(xyz.add(v.xyz));
	}

	/**
	 * subtract two points
	 * 
	 * @param p point
	 * @return vector of substraction
	 */
	public Vector subtract(Point p) {
		return new Vector(xyz.d1 - p.xyz.d1, xyz.d2 - p.xyz.d2, xyz.d3 - p.xyz.d3);

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		return xyz.equals(other.xyz);
	}

	@Override
	public String toString() {
		return xyz.toString();
	}
}