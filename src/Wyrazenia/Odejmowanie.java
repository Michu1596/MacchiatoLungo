package Wyrazenia;

import Instrukcje.Wartosciowanie;

public class Odejmowanie implements Expresion {
    private Expresion wyr1;
    private Expresion wyr2;
    public Odejmowanie(Expresion wyr1, Expresion wyr2){
        this.wyr1 = wyr1;
        this.wyr2 = wyr2;
    }
    @Override
    public int ewaluuj(Wartosciowanie wart){
        return wyr1.ewaluuj(wart) - wyr2.ewaluuj(wart);
    }

    @Override
    public String toString(){
        return wyr1.toString() + " - " + wyr2.toString();
    }
}
