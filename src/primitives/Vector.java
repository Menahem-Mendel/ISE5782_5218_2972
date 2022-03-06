package primitives;

/**
 * Vector defines Point which points from the origin of the coordinate space
 * 
 */
public class Vector extends Point {
	public Vector(double x, double y, double z) {
		super(x, y, z);
		if (Util.isZero(x) && Util.isZero(y) && Util.isZero(z))
			throw new IllegalArgumentException("Cannot define zero vector");
	}

	public Vector(Double3 d) {
		super(d);
	}

	// add returns vector addition of two vectors;
	public Vector add(Vector v) {
		return new Vector(super.add(v).xyz);
	}

	// scale returns scaled vector by factor
	public Vector scale(double d) {
		return new Vector(xyz.scale(d));
	}

	// crossProduct returns cross product of two vectors
	public Vector crossProduct(Vector v) {
		double sample = xyz.d1 / v.xyz.d1;
		if((xyz.d2 / v.xyz.d2)==sample && (xyz.d3 / v.xyz.d3)==sample ){
			throw new IllegalArgumentException("parallel vectors");

		}
		return new Vector(
				new Double3(xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2,
						xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3,
						xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1));
	}

	// lengthSquared returns squared length of the vector
	public double lengthSquared() {
		return Math.pow(xyz.d1, 2d)
				+ Math.pow(xyz.d2, 2d)
				+ Math.pow(xyz.d3, 2d);

	}

	// length returns length of the vector
	public double length() {
		return Math.sqrt(lengthSquared());
	}

	// normalize returns the normalized vector
	public Vector normalize() {
		return new Vector(xyz.reduce(length()));
	}

	// dotProduct returns dot product of two vectors
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
