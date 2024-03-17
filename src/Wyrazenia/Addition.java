package Wyrazenia;

import Instructions.Scope;

public class Addition implements Expression {
    private Expression expression;
    private Expression expression1;
    public Addition(Expression expression, Expression expression1){
        this.expression = expression;
        this.expression1 = expression1;
    }
    @Override
    public int evaluate(Scope scope){
        return expression.evaluate(scope) + expression1.evaluate(scope);
    }

    @Override
    public String toString(){
        return expression.toString() + " + " + expression1.toString();
    }
}
