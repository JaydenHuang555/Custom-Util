package jay.util.math.triangle;

import jay.util.math.Math;

public class TriangleUtil {

    public final static double findAngleSSS(final double a, final double b, final double c) {
        /* cos(c) = (a^2+b^2-c^2)/2ab */
        double theta = jay.util.math.Math.squared(a) + jay.util.math.Math.squared(b) - jay.util.math.Math.squared(c);
        double beta = 2 * a * b;
        return jay.util.math.Math.acos(theta / beta);
    }

    public final static double findAngleLOC(final double A, final double B, final double C) {
        /*cos(c) = (a^2+b^2-C^2)/2ab */
        /* C = a^2+b^2-2abCos(c) */
        double theta = Math.squared(B);
        double beta = 2 * A * B * Math.cos(C);
        return theta - beta;
    }

    public final static double getSinConstant(final double a, final double A) {
        return Math.sin(a) / A;
    }

    public final static double findAngleLOS(final double theta, final double dist) {
        /*
        *    theta = sin(c) / C
        *    theta * c = sin(c)
        *    sin^-1(theta * c) = c
        * */
        double beta = theta * dist;
        return jay.util.math.Math.asin(beta);
    }

    public final static double findAngleLOS(double a, double A, double C) {
        /* sin(c)/C = sin(a) / A */
        return findAngleLOS(getSinConstant(a, A), C);
    }

    public final static double findSideLOS(double theta, double c) {
        /* sin(a)/A = sin(c)/C */
        /* theta = sin(c)/C*/
        /*sin(c)/theta = C*/
        return Math.sin(c) / theta;
    }

    public final static double findSideLOS(double a, double A, double c) {
        return findSideLOS(getSinConstant(a, A), c);
    }

    public final static double area(final double w, final double h) {
        /* 1/2 * (w * h) */
        return (w * h)/2;
    }

    public final static double area(final double a, final double b, final double c) {
        // 1/2 * a * b * sin(c)
        return 1/2 * a * b * Math.sin(c);
    }

}
