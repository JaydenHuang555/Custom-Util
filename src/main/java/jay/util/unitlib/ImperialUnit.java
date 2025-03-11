package jay.util.unitlib;

public class ImperialUnit extends Unit{

    private final double multi;

    protected ImperialUnit(final double multi) {
        super(0);
        this.multi = multi;
    }

    @Override
    public double base() {
        return val / multi;
    }

    @Override
    public double convert(double theta) {
        return theta * multi;
    }
}
