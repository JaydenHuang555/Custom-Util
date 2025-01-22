package jay.util.math;

abstract class Token {

    private final String val;

    Token(final String val){
        this.val = val;
    }

    @Override
    public String toString(){
        return val;
    }

}
