package jay.util.math;

public class ParaToken extends OperatorToken{
    ParaToken() {
        super("(", -1);
    }

    @Override
    public OperandToken eval(OperandToken ... tokens){
        throw new RuntimeException("this class has no evaluations");
    }

}
