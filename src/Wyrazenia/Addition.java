package Wyrazenia;

import Instructions.Scope;

public class Addition implements Expression {
    private Expression wyr1;
    private Expression wyr2;
    public Addition(Expression wyr1, Expression wyr2){
        this.wyr1 = wyr1;
        this.wyr2 = wyr2;
    }
    @Override
    public int evaluate(Scope wart){
        return wyr1.evaluate(wart) + wyr2.evaluate(wart);
    }

    @Override
    public String toString(){
        return wyr1.toString() + " + " + wyr2.toString();
    }
}
