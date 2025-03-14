package jay.util.unitlib;

public class ImperialUnit extends Unit{

    public final static ImperialUnit INCH = new ImperialUnit(12);


    private final double multi;

    protected ImperialUnit(final double multi) {
        super(0);
        this.multi = multi;
    }

}
