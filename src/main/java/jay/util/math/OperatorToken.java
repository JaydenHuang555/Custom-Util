package jay.util.math;

public abstract class OperatorToken extends Token {
    private final int prec;

    protected OperatorToken(final String val, final int prec) {
        super(val);
        this.prec = prec;
    }

    abstract OperandToken eval(OperandToken ... tokens);

    public int prec(){
        return prec;
    }

}
