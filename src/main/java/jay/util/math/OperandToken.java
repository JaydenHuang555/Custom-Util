package jay.util.math;

public class OperandToken extends Token{


    OperandToken(String val) {
        super(val);
    }

    long get(){
        return Long.parseLong(toString());
    }



}
