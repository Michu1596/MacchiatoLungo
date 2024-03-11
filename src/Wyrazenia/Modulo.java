package Wyrazenia;

import Instrukcje.Wartosciowanie;
import Wyjatki.BladWyrazenia;

public class Modulo implements Expression {
    private Expression wyr1;
    private Expression wyr2;
    public Modulo(Expression wyr1, Expression wyr2){
        this.wyr1 = wyr1;
        this.wyr2 = wyr2;
    }
    @Override
    public int ewaluuj(Wartosciowanie wart){
        if(wyr2.ewaluuj(wart) == 0)
            throw new BladWyrazenia("Proba zrobienia modulo 0");
        return wyr1.ewaluuj(wart) % wyr2.ewaluuj(wart);
    }

    @Override
    public String toString(){
        return wyr1.toString() + " % " + wyr2.toString();
    }
}
