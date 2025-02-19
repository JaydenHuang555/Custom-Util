package jay.util.math;

public class ExponentOperatorToken extends OperatorToken{
    protected ExponentOperatorToken() {
        super("^", 3);
    }

    @Override
    OperandToken eval(OperandToken... tokens) {
        return new OperandToken(Math.pwr(tokens[0].get(), tokens[1].get()));
    }
}
