package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Ray;

/**
 * Geometries can hold few different geometries
 */
public class Geometries extends Intersectable {

	private List<Intersectable> list = new LinkedList<>();

	/**
	 * Geometries build ctor
	 * 
	 * @param geometries list who contains geometries
	 */
	public Geometries(Intersectable... geometries) {
		if (geometries.length > 0)
			add(geometries);
	}

	/**
	 * add geometries to the list
	 * 
	 * @param geometries list containing geometries
	 */
	public void add(Intersectable... geometries) {
		list.addAll(List.of(geometries));
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		List<GeoPoint> ret = null;

		for (var g : list) {
			var elems = g.findGeoIntersectionsHelper(ray,maxDistance);

			if (elems == null)
				continue;

			if (ret == null)
				ret = new LinkedList<>(elems);
			else
				ret.addAll(elems);
		}

		return ret;
	}
}
