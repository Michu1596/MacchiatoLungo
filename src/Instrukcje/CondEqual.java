package Instrukcje;

import Wyrazenia.Expression;

public class CondEqual extends Conditional {
    public CondEqual(Expression exp1, Expression exp2){
        super(exp1, exp2);
    }
    @Override
    protected void check(){
        conditionChecked = true;
        conditionOccurred = exp1.evaluate(parentScope) == exp2.evaluate(parentScope);
    }

    @Override
    public String toString(){
        return "IF( " + exp1.toString() + " == " + exp2.toString() + " ){" + '\n' + instructions.toString() + "}" + '\n';
    }
}
