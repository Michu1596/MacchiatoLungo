package Wyrazenia;

import Instructions.Scope;

public class Variable implements Expression {
    final private char variableName;
    public Variable(char variableName){
        this.variableName = variableName;
    }
    @Override
    public int evaluate(Scope scope){
        return scope.get(variableName);
    }
    @Override
    public String toString(){
        return "" + variableName;
    }

}
