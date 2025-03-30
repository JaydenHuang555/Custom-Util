package jay.util.math;

import jay.util.OrderedList;
import jay.util.StringBuilder;

import jay.util.hashtable.HashTable;
import jay.util.stack.Stack;

public class Math {

    public final static double PI = 3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825;

    private final static HashTable<Character, OperatorToken> precMap = new HashTable<>();

    static {
        precMap.put('+', new AddOperatorToken());
        precMap.put('-', new SubOperatorToken());
        precMap.put('*', new MultiOperatorToken());
        precMap.put('%', new ModOperatorToken());
    }

    public static int operatorPrec(final char c){
        if(!precMap.contains(c)) throw new RuntimeException("unsupported operator found");
        return precMap.get(c).prec();
    }

    public final static double pwr(double p, double n){
        if(n == 0) return 1;
        if(n < 0) return 1 / pwr(p, -n);
        else return p * pwr(p, n - 1);
    }

    public final static double squared(double theta) {
        return pwr(theta, 2);
    }

    public final static double factorial(double theta) {
        double ret = 1;
        for(int i = 0; i <= theta; i++) ret = ret * theta;
        return ret;
    }

    public final static long factorial(long b){
        long ret = 1;
        for(int i = 0; i <= b; i++) ret = ret * b;
        return ret;
    }

    public final static double nCr(final double n, final double r) {
        /*nCr = (n!)/(r!*(n-r)!) */
        return factorial(n)/(factorial(r) * factorial(n - r));
    }

    public final static double tan(double theta) {
        return StrictMath.tan(theta);
    }

    public final static double atan(double theta) {
        return StrictMath.atan(theta);
    }

    public final static double cos(double theta) {
        return StrictMath.cos(theta);
    }

    public final static double acos(double theta) {
        return StrictMath.acos(theta);
    }

    public final static double sin(double theta) {
        return StrictMath.sin(theta);
    }

    public final static double asin(double theta) {
        return StrictMath.asin(theta);
    }

    public final static OrderedList<Token> getInfixFromPostfixAsCollection(final String postfix) {
        OrderedList<Token> infix = new OrderedList<>();
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < postfix.length(); i++) {
            throw new RuntimeException("unsupported");
        }
        return infix;
    }

    public final static OrderedList<Token> getPostfixFromInfixAsCollection(final String equation) {
        OrderedList<Token> tokens = new OrderedList<>();
        Stack<Token> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < equation.length(); i++){
            char c = equation.charAt(i);
            boolean isPara = c == '(' || c == ')';
            if(operatorPrec(c) != 0 || isPara){
                if(!builder.isEmpty()){
                    tokens.add(new OperandToken(builder.toString()));
                    builder = new StringBuilder();
                }
                switch(c){
                    case '(':
                        stack.push(new ParaToken());
                        break;
                    case ')':
                        while(!stack.isEmpty() && !(stack.peek() instanceof ParaToken)) {
                            tokens.add(stack.pop());
                        }
                        stack.pop();
                        break;
                    default:
                        while(!stack.isEmpty() && ((OperatorToken)stack.peek()).prec() > operatorPrec(c))
                            tokens.add(stack.pop());
                        stack.push(OperatorToken.of(Character.toString(c)));
                }
            }
            if(('0' - 1 < c && c < '9' + 1) || c == '.') builder.append(c);
        }
        if(!builder.isEmpty()) tokens.add(new OperandToken(builder.toString()));
        while(!stack.isEmpty())
            tokens.add(stack.pop());
        return tokens;
    }

    public static final String getPostfixFromInfixAsString(String infix) {
        OrderedList<Token> tokens = getPostfixFromInfixAsCollection(infix);
        StringBuilder builder = new StringBuilder();
        for(Token token : tokens) {
            builder.append(token.toString());
        }
        return builder.toString();
    }

    /**
     * @param equation string form of the equation
     * @return the pemdas evaluation
     * uses postfix notation and the shunting algorithm for efficiency
     */
    public final static double eval(final String equation){
        OrderedList<Token> tokens = getPostfixFromInfixAsCollection(equation);
        Stack<Token> stack = new Stack<>();
        for(int i = 0; i < tokens.size(); i++){
            Token token = tokens.get(i);
            if(token instanceof OperandToken) stack.push(token);
            if(token instanceof OperatorToken){
                OperandToken r = (OperandToken) stack.pop();
                OperandToken l = (OperandToken) stack.pop();
                stack.push(((OperatorToken)token).eval(l,r));
            }
        }
        return ((OperandToken)stack.pop()).get();
    }

    public double abs(double theta) {
        return theta * -1;
    }

    public static boolean epsilonEquals(double theta, double beta, double epsilon) {
        return theta + epsilon <= beta || theta - epsilon >= beta;
    }

    public static boolean epsilonEquals(long theta, long beta, long epsilon) {
        return theta + epsilon <= beta || theta - epsilon >= beta;
    }

    public static boolean epsilonEquals(int theta, int beta, int epsilon) {
        return theta + epsilon <= beta || theta - epsilon >= beta;
    }

}
