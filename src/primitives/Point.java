package primitives;

/**
 * Point class is a single unit representing the dot in space
 */
public class Point {

	protected final Double3 xyz;
	public static final Point ZERO = new Point(Double3.ZERO);

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
	 * add returns point of sum of vector and point
	 * 
	 * @param rhs vector
	 * @return vector of sum
	 */
	public Point add(Vector rhs) {
		return new Point(xyz.add(rhs.xyz));
	}

	/**
	 * sub returns new Vector by substructing two points
	 * 
	 * @param rhs point
	 * @return vector of subion
	 */
	public Vector sub(Point rhs) {
		return new Vector(xyz.sub(rhs.xyz));
	}

	/**
	 * distquared returns squared distance between two points
	 * 
	 * @param rhs point
	 * @return squared distance between two points
	 */
	public double distSq(Point rhs) {
		try {
			return sub(rhs).lengthSq();
		} catch (IllegalArgumentException e) {
			return 0;
		}
	}

	/**
	 * dist returns distance between two points
	 * 
	 * @param rhs point
	 * @return distance between two points
	 */
	public double dist(Point rhs) {
		return Math.sqrt(distSq(rhs));
	}

	/**
	 * getXYZ returns Double3 value of the point
	 * 
	 * @return xyz Double3
	 */
	public Double3 getXYZ() {
		return xyz;
	}

	/**
	 * getX returns x
	 * 
	 * @return value of cordinate x
	 */
	public double getX() {
		return xyz.d1;
	}

	/**
	 * getY returns y
	 * 
	 * @return value of cordinate y
	 */
	public double getY() {
		return xyz.d2;
	}

	/**
	 * getZ returns z
	 * 
	 * @return value of cordinate z
	 */
	public double getZ() {
		return xyz.d3;
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