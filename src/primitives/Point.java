package primitives;

/**
 * Point class is a single unit representing the dot in space
 * 
 */
public class Point {
	protected final Double3 xyz;

	public Point(double x, double y, double z) {
		xyz = new Double3(x, y, z);
	}

	public Point(Double3 d) {
		xyz = d;
	}

	public Double3 getXYZ() {
		return xyz;
	}

	// return sum of two points
	public Point add(Vector p) {
		return new Point(xyz.add(p.xyz));
	}

	// substarct returns point of difference of two points
	public Point subtract(Point p) {
		return new Point(xyz.subtract(p.xyz));
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