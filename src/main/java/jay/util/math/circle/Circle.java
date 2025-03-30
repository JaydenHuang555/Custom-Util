package jay.util.math.circle;

public class Circle {

    private final double radius;

    public Circle(final double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return CircleUtil.area(radius);
    }

    public double getCircumfrence() {
        return CircleUtil.circumfrence(radius);
    }

    public double getDiameter() {
        return CircleUtil.diamterFromRadius(radius);
    }

    public double getRadius() {
        return radius;
    }
}
