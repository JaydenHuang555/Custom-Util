package jay.util.math.circle;

import jay.util.math.Math;

public class CircleUtil {

    public final static double area(final double r) {
        return jay.util.math.Math.PI * jay.util.math.Math.squared(r);
    }

    public final static double circumfrence(final double r) {
        return Math.PI * 2 * r;
    }

    public final static double radiusFromDiamater(final double d) {
        return d / 2;
    }

    public final static double diamterFromRadius(final double r) {
        return r * 2;
    }

    public final static double subCircleArea(final double r, final double theta) {
        return area(r) / theta;
    }

    public final static double semiCircleArea(final double r) {
        return subCircleArea(r, 2.0);
    }
}
