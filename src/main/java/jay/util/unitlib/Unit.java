package jay.util.unitlib;

/**
 * class representing a unit of some sort
 * does not handle unit conversions outside its range
 * (ex: inch -> feet (valid), inch -> meter (invalid))
 */
public abstract class Unit {

    protected double val;

    protected Unit(double val) {
        this.val = val;
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

    public abstract double base();

    public abstract double convert(double theta);

    public double in(Unit unit) {
        return unit.convert(this);
    }



}
