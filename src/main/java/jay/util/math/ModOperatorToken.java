package jay.util.math;

public class ModOperatorToken extends OperatorToken{
    protected ModOperatorToken() {
        super("%", 2);
    }

    @Override
    OperandToken eval(OperandToken... tokens) {
        return new OperandToken(tokens[0].get() % tokens[1].get());
    }
}
