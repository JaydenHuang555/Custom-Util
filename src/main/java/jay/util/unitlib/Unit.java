package jay.util.unitlib;

/**
 * class representing a unit of some sort
 * does not handle unit conversions outside its range
 * (ex: inch -> feet (valid), inch -> meter (invalid))
 */
public abstract class Unit {

    protected double val;
    private final double mutli;

    protected Unit(double multi) {
        this.mutli = multi;
    }

    public Unit of(double theta) {
        val = theta;
        return this;
    }

    public Unit of(Unit unit) {
        val = convert(unit);
        return this;
    }

    public double convert(Unit unit) {
        return convert(unit.base());
    }

    public double base() {
        return val / mutli;
    }

    public double convert(double theta) {
        return theta * mutli;
    }

    public double in(Unit unit) {
        return unit.convert(this);
    }



}
