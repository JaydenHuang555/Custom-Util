package jay.util.math;

public abstract class OperatorToken extends Token {
    private final int prec;

    protected OperatorToken(final String val, final int prec) {
        super(val);
        this.prec = prec;
    }

    public static OperatorToken of(final String s){
        switch(s){
            case "+": return new AddOperatorToken();
            case "-": return new SubOperatorToken();
            case "*": return new MultiOperatorToken();
            case "%" : return new ModOperatorToken();
            default: throw new RuntimeException("invalid operator found");
        }
    }

    abstract OperandToken eval(OperandToken ... tokens);

    public int prec(){
        return prec;
    }

}
