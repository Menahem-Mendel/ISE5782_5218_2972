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
	 * @param d 3D point
	 */
	public Vector(Double3 d) {
		super(d);
		if (xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException(Errors.ZERO_VEC);
	}

	/**
	 * add returns vector of sum of two vectors
	 * 
	 * @param v
	 * @return vector of sum
	 */
	public Vector add(Vector v) {
		return new Vector(super.add(v).xyz);
	}

	/**
	 * scale returns scaled vector by the factor parametere
	 * 
	 * @param d
	 * @return scaled vector
	 */
	public Vector scale(double d) {
		return new Vector(xyz.scale(d));
	}

	/**
	 * crossProduct returns cross product of two vectors
	 * 
	 * @param rhs vector
	 * @return vector cross product
	 */
	public Vector crossProduct(Vector rhs) {
		return new Vector(xyz.d2 * rhs.xyz.d3 - xyz.d3 * rhs.xyz.d2,
				xyz.d3 * rhs.xyz.d1 - xyz.d1 * rhs.xyz.d3,
				xyz.d1 * rhs.xyz.d2 - xyz.d2 * rhs.xyz.d1);
	}

	/**
	 * lengthSquared returns squared length of the vector
	 * 
	 * @return vector squared length
	 */
	public double lengthSquared() {
		return Math.abs(dotProduct(this));
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
	 * dotProduct returns dot product of two vectors
	 * 
	 * @param rhs vector
	 * @return dot product
	 */
	public double dotProduct(Vector rhs) {
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
