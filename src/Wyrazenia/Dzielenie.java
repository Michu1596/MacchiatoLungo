package Wyrazenia;

import Instrukcje.Wartosciowanie;
import Wyjatki.BladWyrazenia;

public class Dzielenie implements Wyrazenie{
    private Wyrazenie wyr1;
    private Wyrazenie wyr2;
    public Dzielenie(Wyrazenie wyr1, Wyrazenie wyr2){
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
        return wyr1.toString() + " + " + wyr2.toString();
    }
}
