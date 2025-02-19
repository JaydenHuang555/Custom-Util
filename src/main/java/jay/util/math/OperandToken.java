package jay.util.math;

public class OperandToken extends Token{

    OperandToken(final double c){
        super(String.valueOf(c));
    }

    OperandToken(String val) {
        super(val);
    }

    double get(){
        return Double.parseDouble(toString());
    }

}
