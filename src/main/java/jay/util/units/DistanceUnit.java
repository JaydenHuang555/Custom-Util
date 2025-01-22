package jay.util.units;

public abstract class DistanceUnit {

    private double baseUnitVal;

    public DistanceUnit(){
        this.baseUnitVal = 0;
    }

    public abstract int convert(double i);

}
