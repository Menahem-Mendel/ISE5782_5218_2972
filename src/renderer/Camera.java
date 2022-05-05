package renderer;

import java.util.MissingResourceException;
import java.util.stream.Stream;

import primitives.*;

/**
 * Camera represents point from which we are looking and a view plane which
 * shows all objects behind
 */
public class Camera {
	private Point p0;
	private Vector vTo, vUp, vRight;
	private double width, height, dist;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracerBase;

	/**
	 * Camera build ctor
	 * 
	 * @param p  starting point
	 * @param to vector
	 * @param up vector
	 * @throws IllegalArgumentException if to and up vectors are not orthogonal
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
	 * setVPSize sets the view plane size in pixels
	 * 
	 * @param w view plane width
	 * @param h view plane height
	 * @return camera object
	 * @throws IllegalArgumentException if width of height is have non-positive
	 *                                  values
	 */
	public Camera setVPSize(double w, double h) {
		if (Util.alignZero(w) <= 0)
			throw new IllegalArgumentException(Errors.NON_POS);
		width = w;

		if (Util.alignZero(h) <= 0)
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
		if (Util.alignZero(d) <= 0)
			throw new IllegalArgumentException(Errors.NON_POS);
		dist = d;

		return this;
	}

	/**
	 * setImageWriter sets the image writer object
	 * 
	 * @param image image writer object
	 * @return camera object
	 */
	public Camera setImageWriter(ImageWriter image) {
		imageWriter = image;

		return this;
	}

	/**
	 * setRayTracer sets the ray tracer object
	 * 
	 * @param ray tracer for the rays
	 * @return camera object
	 */
	public Camera setRayTracer(RayTracerBase ray) {
		rayTracerBase = ray;

		return this;
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

		double yI = -(i - Util.alignZero((nY - 1) / 2d)) * rY;
		double xJ = (j - Util.alignZero((nX - 1) / 2d)) * rX;

		if (!Util.isZero(xJ))
			pIJ = pIJ.add(vRight.scale(xJ));

		if (!Util.isZero(yI))
			pIJ = pIJ.add(vUp.scale(yI));

		return new Ray(p0, pIJ.sub(p0));
	}

	/**
	 * loop over all pixels on view plane, wirting the correct color of each pixel
	 */
	public void renderImage() {
		if (Stream.of(p0, vRight, vTo, vUp, rayTracerBase, imageWriter).anyMatch(x -> x == null)
				|| Stream.of(height, width, dist).anyMatch(x -> x == 0))
			throw new MissingResourceException("Some of the fields aren't initialized", "Camera", "");

		int nx = imageWriter.getNx();
		int ny = imageWriter.getNy();

		for (int i = 0; i < nx; i++)
			for (int j = 0; j < ny; j++)
				imageWriter.writePixel(i, j, castRay(nx, ny, i, j));
	}

	/**
	 * call by delegation the method writeToImage
	 */
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("Some of the fields aren't initialized", "Camera", "imageWriter");

		imageWriter.writeToImage();
	}

	/**
	 * creates ray and returns its color
	 * 
	 * @param Nx number of colums
	 * @param Ny number of rows
	 * @param i  pixel column index
	 * @param j  pixel row index
	 * @return color
	 */
	private Color castRay(int Nx, int Ny, int i, int j) {
		Ray ray = constructRay(Nx, Ny, i, j);

		return rayTracerBase.traceRay(ray);
	}

	/**
	 * print image's grid
	 * 
	 * @param delta size of the grid
	 * @param color of the grid
	 */
	public void printGrid(int delta, Color color) {
		if (imageWriter == null)
			throw new MissingResourceException("this image not initialized yet", "Camera", "");

		int nx = imageWriter.getNx();
		int ny = imageWriter.getNy();

		for (int i = 0; i < nx; i += delta)
			for (int j = 0; j < ny; j++)
				imageWriter.writePixel(i, j, color);

		for (int i = 0; i < nx; i++)
			for (int j = 0; j < ny; j += delta)
				imageWriter.writePixel(i, j, color);
	}
}
