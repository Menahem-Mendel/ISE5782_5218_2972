package geometries;

import primitives.*;

/**
 * Geometry interface describes every geometry in multi-dimensional space
 */
public abstract class Geometry extends Intersectable {

	protected Color emission = Color.BLACK;
	private Material material =new Material();

	public Material getMaterial(){
		return material;
	}

	public Geometry setMaterial(Material m){
		material = m;
		return this;
	}

	/**
	 * getNormal returns normal vector of a particular point on the figure
	 * 
	 * @param p 3D point
	 * @return vector normal
	 */
	public abstract Vector getNormal(Point p);

	/**
	 * getEmission returns emission light
	 * 
	 * @return emission
	 */
	public Color getEmission() {
		return emission;
	}

	/**
	 * setEmission sets the new value to the emission color
	 * 
	 * @param emission color
	 * @return the current object
	 */
	public Geometry setEmission(Color emission) {
		this.emission = emission;

		return this;
	}
}
