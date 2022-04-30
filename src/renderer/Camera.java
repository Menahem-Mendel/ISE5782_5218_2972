package renderer;

import java.util.MissingResourceException;

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

	/**
	 * setImageWriter sets the image writer object
	 * 
	 * @param image
	 * @return camera object
	 */
	public Camera setImageWriter(ImageWriter image) {
		imageWriter = image;

		return this;
	}

	/**
	 * setRayTracer sets the ray tracer object
	 * 
	 * @param ray
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

		double yI = -(i - Util.alignZero((double) (nY - 1) / 2)) * rY;
		double xJ = (j - Util.alignZero((double) (nX - 1) / 2)) * rX;

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
		if (p0 == null || vRight == null || vTo == null || vUp == null || height == 0 || width == 0 || dist == 0
				|| rayTracerBase == null || imageWriter == null)
			throw new MissingResourceException("Some of the fields aren't initialized", "Camera", "");

		for (int i = 0; i < imageWriter.getNx(); i++)
			for (int j = 0; j < imageWriter.getNy(); j++) {
				Ray ray = constructRay(imageWriter.getNx(), imageWriter.getNy(), i, j);
				Color color = castRay(ray);
				imageWriter.writePixel(i, j, color);
			}
	}

	/**
	 *  call by delegation the method writeToImage
	 */
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("Some of the fields aren't initialized", "Camera", "imageWriter");

		imageWriter.writeToImage();
	}

	/**
	 * 
	 * @param ray
	 * @return
	 */
	private Color castRay(Ray ray) {
		return rayTracerBase.traceRay(ray);
	}

	/**
	 * print image's grid
	 * 
	 * @param interval size of the grid
	 * @param color of the grid
	 */
	public void printGrid(int interval, Color color) {
		if (imageWriter == null)
			throw new MissingResourceException("this image not initialized yet", "Camera", "");

		for (int i = 0; i < imageWriter.getNx(); i += interval)
			for (int j = 0; j < imageWriter.getNy(); j++)
				imageWriter.writePixel(i, j, color);

		for (int i = 0; i < imageWriter.getNx(); i++)
			for (int j = 0; j < imageWriter.getNy(); j += interval)
				imageWriter.writePixel(i, j, color);
	}
}
