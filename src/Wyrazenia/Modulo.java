package Wyrazenia;

import Instructions.Scope;
import Exceptions.ExpressionError;

public class Modulo implements Expression {
    private Expression expression;
    private Expression expression1;
    public Modulo(Expression expression, Expression expression1){
        this.expression = expression;
        this.expression1 = expression1;
    }
    @Override
    public int evaluate(Scope scope){
        if(expression1.evaluate(scope) == 0)
            throw new ExpressionError("Modulo 0");
        return expression.evaluate(scope) % expression1.evaluate(scope);
    }

    @Override
    public String toString(){
        return expression.toString() + " % " + expression1.toString();
    }
}
