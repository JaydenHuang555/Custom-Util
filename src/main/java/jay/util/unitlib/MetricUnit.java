package jay.util.unitlib;

import jay.util.math.Math;

public class MetricUnit extends Unit {

    public final static MetricUnit BASE = new MetricUnit(1);
    public final static MetricUnit NANO = new MetricUnit(-9);
    public final static MetricUnit MILLI = new MetricUnit(-3);
    public final static MetricUnit KILO = new MetricUnit(3);

    private final double multi;
    private double val = 0.0;

    /**
     * conversion: 10^exp
    * */
    public MetricUnit(final double exp) {
        super(0);
        this.multi = Math.pwr(10, exp);
    }

    public double base() {
        return val / multi;
    }

    @Override
    public double convert(double theta) {
        return theta * multi;
    }
}
