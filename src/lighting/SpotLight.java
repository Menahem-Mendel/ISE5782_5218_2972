package lighting;

import primitives.*;

public class SpotLight extends PointLight {

    private final Vector direction;

    public SpotLight(Color color, Point posit, Vector dir) {
        super(color, posit);
        direction = dir.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        double cosTetha = direction.dot(getL(p));
        Color inten = super.getIntensity(p);
        return inten.scale(Math.max(0,cosTetha));
    }


}
