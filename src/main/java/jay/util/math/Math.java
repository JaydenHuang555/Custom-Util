package jay.util.math;

import jay.util.OrderdList;
import jay.util.StringBuilder;

import java.util.Stack;

public class Math {

    public static int operatorPrec(final char c){
        switch(c){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default: return 0;
        }
    }

    public final static double eval(final String equation){

        OrderdList<Token> tokens = new OrderdList<>();
        Stack<Token> stack = new Stack<>();

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < equation.length(); i++){
            char c = equation.charAt(i);
            if(operatorPrec(c) != 0 || c == '(' || c == ')'){
                if(!builder.isEmpty()){
                    tokens.add(new OperandToken(builder.toString()));
                    builder = new StringBuilder();
                }
                while(!stack.isEmpty() && ((OperatorToken)stack.peek()).prec() > operatorPrec(c))
                    tokens.add(stack.pop());

            }
        }

        return 0;
    }

}
