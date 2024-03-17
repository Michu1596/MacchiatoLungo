package Wyrazenia;

import Instructions.Scope;

public class Literal implements Expression {
    final private int value;
    public Literal(int value){
        this.value = value;
    }
    @Override
    public int evaluate(Scope scope){
        return value;
    }
    public int ewaluuj(){
        return value;
    }

    @Override
    public String toString(){
        return Integer.toString(value);
    }
}
