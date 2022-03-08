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
	 * @param x coordinate
	 * @param y coordinate
	 * @param z coordinate
	 */
	public Point(double x, double y, double z) {
		this(new Double3(x, y, z));
	}

	/**
	 * Point build ctor
	 * 
	 * @param rhs 3D double
	 */
	public Point(Double3 rhs) {
		xyz = rhs;
	}

	/**
	 * add vector to a point
	 * 
	 * @param rhs vector
	 * @return vector of sum
	 */
	public Point add(Vector rhs) {
		return new Point(xyz.add(rhs.xyz));
	}

	/**
	 * subtract two points
	 * 
	 * @param rhs point
	 * @return vector of subtraction
	 */
	public Vector subtract(Point rhs) {
		return new Vector(xyz.subtract(rhs.xyz));
	}

	/**
	 * dist returns distance between two points
	 * 
	 * @param rhs point
	 * @return distance between two points
	 */
	public double dist(Point rhs) {
		return subtract(rhs).length();
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