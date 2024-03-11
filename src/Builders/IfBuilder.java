package Builders;

import Instrukcje.*;
import Wyrazenia.Expression;

public class IfBuilder extends Builder{
    protected Conditional cond;
    public IfBuilder(Builder outerScope, String condition, Expression exp1, Expression exp2){
        super(outerScope);
        switch (condition){
            case "<":
                cond = new CondLess(exp1, exp2);
                break;
            case "==":
                cond = new CondEqual(exp1, exp2);
                break;
            case ">":
                cond = new CondGreater(exp1, exp2);
                break;
            case "<=":
                cond = new CondLessEq(exp1, exp2);
                break;
            case ">=":
                cond = new CondGreaterEq(exp1, exp2);
                break;
        }
        instructionNesting.peek().addIntruction(cond);
        instructionNesting.push(cond);
    }

    @Override
    public Builder closeScope(){
        instructionNesting.pop();
        return parent;
    }
}
