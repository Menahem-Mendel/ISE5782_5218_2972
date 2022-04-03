package renderer;

import primitives.*;

/**
 * Camera represents point from which we are looking and a view plane which
 * shows all objects behind
 */
public class Camera {
	private Vector vTo, vUp, vRight;
	private Point p0;
	private double width, height, dist;

	/**
	 * Camera build ctor
	 * 
	 * @param p  starting point
	 * @param to vector
	 * @param up vector
	 * @throws IllegalArgumentException if to and up vectors are not orthogonal
	 * 
	 * 
	 * 
	 */
	public Camera(Point p, Vector to, Vector up) {
		if (!Util.isZero(to.dot(up)))
			throw new IllegalArgumentException(Errors.NOT_ORTHOGONAL_VEC);

		p0 = p;
		vUp = up.normalize();
		vTo = to.normalize();
		vRight = vTo.cross(vUp); // cross product of normalized vectors is normalized
	}

	/**
	 * getVTo get to vector
	 * 
	 * @return to vector
	 */
	public Vector getVTo() {
		return vTo;
	}

	/**
	 * getVUp get up vector
	 * 
	 * @return up vector
	 */
	public Vector getVUp() {
		return vUp;
	}

	/**
	 * getVRight get right vector
	 * 
	 * @return right vector
	 */
	public Vector getVRight() {
		return vRight;
	}

	/**
	 * constructRay construct ray through the pixel on the view plane
	 * 
	 * @param nX number of columns
	 * @param nY number of rows
	 * @param j  pixel column index
	 * @param i  pixel row index
	 * @return ray on the view plane pixel
	 */
	public Ray constructRay(int nX, int nY, int j, int i) {
		Point pC = p0.add(vTo.scale(dist));
		Point pIJ = pC;

		double rY = height / nY;
		double rX = width / nX;

		double yI = -(i - (nY - 1d) / 2d) * rY;
		double xJ = (j - (nX - 1d) / 2d) * rX;

		if (!Util.isZero(xJ))
			pIJ = pIJ.add(vRight.scale(xJ));

		if (!Util.isZero(yI))
			pIJ = pIJ.add(vUp.scale(yI));

		return new Ray(p0, pIJ.sub(p0));
	}

	/**
	 * setVPSize sets the view plane size in pixels
	 * 
	 * @param w view plane width
	 * @param h view plane height
	 * @return camera object
	 * @throws IllegalArgumentException if width of height is have non-positive
	 *                                  values
	 */
	public Camera setVPSize(double w, double h) {
		if (w <= 0)
			throw new IllegalArgumentException(Errors.NON_POS);
		width = w;

		if (h <= 0)
			throw new IllegalArgumentException(Errors.NON_POS);
		height = h;

		return this;
	}

	/**
	 * setVPDistance sets the view plane distance from the camera
	 * 
	 * @param distance from camera
	 * @return camera object
	 */
	public Camera setVPDistance(double d) {
		if (d <= 0)
			throw new IllegalArgumentException(Errors.NON_POS);
		dist = d;

		return this;
	}
}
