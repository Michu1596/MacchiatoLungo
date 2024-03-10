package Wyrazenia;

import Instrukcje.Wartosciowanie;

public class Zmienna implements Expresion {
    final private char nazwaZmiennej;
    public Zmienna(char nazwaZmiennej){
        this.nazwaZmiennej = nazwaZmiennej;
    }
    @Override
    public int ewaluuj(Wartosciowanie wart){
        return wart.get(nazwaZmiennej);
    }
    @Override
    public String toString(){
        return "" + nazwaZmiennej;
    }

}
