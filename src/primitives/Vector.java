package primitives;

/*
 * Vector class represents a vector in 3D space
 * 
 */
public class Vector extends Point {

	/**
	 * Vector build ctor
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @param z coordinate
	 */
	public Vector(double x, double y, double z) {
		this(new Double3(x, y, z));
	}

	/**
	 * Vector build ctor
	 * 
	 * @param rhs 3D double
	 */
	public Vector(Double3 rhs) {
		super(rhs);

		if (xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException(Errors.ZERO_VEC);
	}

	/**
	 * add returns vector of sum of two vectors
	 * 
	 * @param rhs vector
	 * @return vector of sum
	 */
	public Vector add(Vector rhs) {
		return new Vector(super.add(rhs).xyz);
	}

	/**
	 * scale returns scaled vector by the factor parameter
	 * 
	 * @param rhs scalar
	 * @return scaled vector
	 */
	public Vector scale(double rhs) {
		return new Vector(xyz.scale(rhs));
	}

	/**
	 * cross returns cross product of two vectors
	 * 
	 * @param rhs vector
	 * @return vector cross product
	 */
	public Vector cross(Vector rhs) {
		return new Vector(
				xyz.d2 * rhs.xyz.d3 - xyz.d3 * rhs.xyz.d2,
				-(xyz.d1 * rhs.xyz.d3 - xyz.d3 * rhs.xyz.d1),
				xyz.d1 * rhs.xyz.d2 - xyz.d2 * rhs.xyz.d1);
	}

	/**
	 * lengthSquared returns squared length of the vector
	 * 
	 * @return vector squared length
	 */
	public double lengthSquared() {
		return Math.abs(dot(this));
	}

	/**
	 * length returns length of the vector
	 * 
	 * @return vector length
	 */
	public double length() {
		return Math.sqrt(lengthSquared());
	}

	/**
	 * normalize returns the normalized vector
	 * 
	 * @return normal vector
	 */
	public Vector normalize() {
		return new Vector(xyz.reduce(length()));
	}

	/**
	 * dot returns dot product of two vectors
	 * 
	 * @param rhs vector
	 * @return dot product
	 */
	public double dot(Vector rhs) {
		return xyz.d1 * rhs.xyz.d1 +
				xyz.d2 * rhs.xyz.d2 +
				xyz.d3 * rhs.xyz.d3;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		Vector other = (Vector) obj;
		return super.equals(other);
	}

	@Override
	public String toString() {
		return "->" + super.toString();
	}
}
