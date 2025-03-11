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

    public final static long factorial(long b){
        long ret = 1;
        for(int i = 0; i <= b; i++) ret = ret * b;
        return ret;
    }

    /**
     *
     * @param equation string form of the equation
     * @return the pemdas evaluation
     *
     * uses postfix notation and the shunting algorithm for efficiency
     */
    public final static double eval(final String equation){
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

}
