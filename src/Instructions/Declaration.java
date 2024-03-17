package Instructions;

import Exceptions.ExpressionError;
import Wyrazenia.Expression;

public class Declaration extends SingleInstruction {
    private char name;

    private Expression expression;
    public Declaration(char name, Expression expression){
        super();
        this.name = name;
        this.expression = expression;
    }

    public void execute(){
        try {
            parentScope.declare(name, expression.evaluate(parentScope));
        }
        catch (ExpressionError e){
            e.currentValuation = parentScope;
            e.invalidInstruction = this;
        }
    }
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString(){
        if(expression == null)
            return "DECLARATION: " + name + " = NULL\n";
        else
            return "DECLARATION: " + name + " = " + expression.toString() + '\n';
    }
}
