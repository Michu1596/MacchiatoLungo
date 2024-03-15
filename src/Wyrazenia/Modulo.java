package Wyrazenia;

import Instructions.Scope;
import Wyjatki.ExpressionError;

public class Modulo implements Expression {
    private Expression wyr1;
    private Expression wyr2;
    public Modulo(Expression wyr1, Expression wyr2){
        this.wyr1 = wyr1;
        this.wyr2 = wyr2;
    }
    @Override
    public int evaluate(Scope wart){
        if(wyr2.evaluate(wart) == 0)
            throw new ExpressionError("Proba zrobienia modulo 0");
        return wyr1.evaluate(wart) % wyr2.evaluate(wart);
    }

    @Override
    public String toString(){
        return wyr1.toString() + " % " + wyr2.toString();
    }
}
