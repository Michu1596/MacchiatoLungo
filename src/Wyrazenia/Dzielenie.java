package Wyrazenia;

import Instrukcje.Wartosciowanie;
import Wyjatki.BladWyrazenia;

public class Dzielenie implements Expresion {
    private Expresion wyr1;
    private Expresion wyr2;
    public Dzielenie(Expresion wyr1, Expresion wyr2){
        this.wyr1 = wyr1;
        this.wyr2 = wyr2;
    }
    @Override
    public int ewaluuj(Wartosciowanie wart){
        if(wyr2.ewaluuj(wart) == 0)
            throw new BladWyrazenia("Proba podznielenia przez zero");
        return wyr1.ewaluuj(wart) / wyr2.ewaluuj(wart);
    }

    @Override
    public String toString(){
        return wyr1.toString() + " / " + wyr2.toString();
    }
}
