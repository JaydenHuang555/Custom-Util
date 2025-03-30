package jay.util.math.triangle;

public class Triangle {

    private double a, b ,c, A, B, C, LOS_CONSTANT;

    public Triangle(double a, double b, double c, double A, double B, double C, final double LOS_CONSTANT) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.A = A;
        this.B = B;
        this.C = C;
        this.LOS_CONSTANT = LOS_CONSTANT;
    }

    public Triangle(double a, double b, double c, double A, double B, double C) {
        this(
                a,
                b,
                c,
                A,
                B,
                C,
                TriangleUtil.getSinConstant(a, A)
        );
    }

    public Triangle(double a, double b, double c) {
        this(
                a,
                b,
                c,
                TriangleUtil.findAngleSSS(b, c, a),
                TriangleUtil.findAngleSSS(c, a, b),
                TriangleUtil.findAngleSSS(a, c, c)
        );
    }

    public double getArea() {
        return TriangleUtil.area(a, b, c);
    }

    public double getPerimeter() {
        return a + b + c;
    }

}
