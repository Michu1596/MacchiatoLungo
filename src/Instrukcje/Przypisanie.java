package Instrukcje;

import Wyjatki.MacchiatosError;
import Wyrazenia.Expression;

public class Przypisanie extends SingleInstruction {
    final private char name;
    private Expression expression;
    public Przypisanie(char name, Expression expression){
        super();
        this.name = name;
        this.expression = expression;
    }

    @Override
    public void execute(){
        try {
            parentScope.set(name, expression.evaluate(parentScope));
        }
        catch (MacchiatosError e){
            e.invalidInstruction = this;
            e.currentValuation = parentScope;
            throw e;
        }
    }

    @Override
    public String toString(){
        return "ASSIGNMENT: " + name + " = " + expression.toString() + '\n';
    }

}
