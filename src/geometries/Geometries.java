package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;

/**
 * Geometries can hold few different geometries
 */
public class Geometries implements Intersectable {

	private List<Intersectable> list;

	/**
	 * Geometries build ctor
	 * 
	 * @param geometries list who contains geometries
	 */
	public Geometries(Intersectable... geometries) {
		list = new LinkedList<Intersectable>();

		for (int i = 0; i < geometries.length; i++) {
			list.add(geometries[i]);
		}
	}

	/**
	 * add geometries to the list
	 * 
	 * @param geometries list containing geometries
	 */
	public void add(Intersectable... geometries) {
		for (int i = 0; i < geometries.length; i++) {
			list.add(geometries[i]);
		}
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		List<Point> ret = new LinkedList<Point>();
		List<Point> elems = new LinkedList<Point>();

		for (int i = 0; i < list.size(); i++) {
			elems = list.get(i).findIntersections(ray);

			if (elems == null)
				continue;

			ret.addAll(elems);
		}

		return (ret.size() > 0) ? ret : null;
	}
}
