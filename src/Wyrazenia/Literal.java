package Wyrazenia;

import Instrukcje.Wartosciowanie;

public class Literal implements Expression {
    final private int wartosc;
    public Literal(int wartosc){
        this.wartosc = wartosc;
    }
    @Override
    public int ewaluuj(Wartosciowanie wart){
        return wartosc;
    }
    public int ewaluuj(){
        return wartosc;
    }

    @Override
    public String toString(){
        return Integer.toString(wartosc);
    }
}
