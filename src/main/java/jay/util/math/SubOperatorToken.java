package jay.util.math;

public class SubOperatorToken extends OperatorToken{
    protected SubOperatorToken() {
        super("-", 1);
    }

    @Override
    OperandToken eval(OperandToken... tokens) {
        return new OperandToken(tokens[0].get() - tokens[1].get());
    }
}
