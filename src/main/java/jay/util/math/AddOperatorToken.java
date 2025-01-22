package jay.util.math;

public class AddOperatorToken extends OperatorToken{
    protected AddOperatorToken() {
        super("+", 1);
    }

    @Override
    OperandToken eval(OperandToken... tokens) {
        return new OperandToken(tokens[0].get() + tokens[1].get());
    }
}
