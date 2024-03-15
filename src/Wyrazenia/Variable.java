package Wyrazenia;

import Instrukcje.Scope;

public class Variable implements Expression {
    final private char nazwaZmiennej;
    public Variable(char nazwaZmiennej){
        this.nazwaZmiennej = nazwaZmiennej;
    }
    @Override
    public int evaluate(Scope wart){
        return wart.get(nazwaZmiennej);
    }
    @Override
    public String toString(){
        return "" + nazwaZmiennej;
    }

}
