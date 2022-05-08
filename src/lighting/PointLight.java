package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    private final Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    public PointLight(Color color, Point posit) {
        super(color);
        position = posit;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = position.dist(p);
        double attenuation = 1d / (kC + kL * d + kQ * d * d);
        return intensity.scale(attenuation);
    }

    @Override
    public Vector getL(Point p) {
        return p.sub(position).normalize();
    }

    public PointLight setKc(double c){
        kC=c;
        return this;
    }

    public PointLight setKl(double l){
        kL=l;
        return this;
    }

    public PointLight setKq(double q){
        kQ=q;
        return this;
    }
}
