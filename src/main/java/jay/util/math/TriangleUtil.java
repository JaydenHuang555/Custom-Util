package jay.util.math;

public class TriangleUtil {

    public final static double findAngleSSS(final double a, final double b, final double c) {
        /* cos(c) = (a^2+b^2-c^2)/2ab */
        double theta = Math.squared(a) + Math.squared(b) - Math.squared(c);
        double beta = 2 * a * b;
        return Math.acos(theta / beta);
    }

    public final static double findAngleLOC(final double A, final double B, final double C) {
        /*cos(c) = (a^2+b^2-C^2)/2ab */
        /* C = a^2+b^2-2abCos(c) */
        double theta = Math.squared(A) + Math.squared(B);
        double beta = 2 * A * B * Math.cos(C);
        return theta - beta;
    }

    public final static double findAngleLOS(final double theta, final double dist) {

        /*
        *    theta = sin(c) / C
        *    theta * c = sin(c)
        *    sin^-1(theta * c) = c
        * */
        double beta = theta * dist;
        return Math.asin(beta);
    }

    public final static double findAngleLOS(double a, double A, double C) {
        /* sin(c)/C = sin(a) / A */
        return findAngleLOS(Math.sin(a) / A, C);
    }

}
