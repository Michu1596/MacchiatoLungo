package Instrukcje;

import Wyjatki.BladWyrazenia;
import Wyrazenia.Expresion;

public class Deklaracja extends InstrukcjaPojedyncza{
    private char nazwa;

    private Expresion expresion;
    public Deklaracja(char nazwa, Expresion expresion){
        super();
        this.nazwa = nazwa;
        this.expresion = expresion;
    }

    public void wykonaj(){
        try {
            wartNadrzedne.deklaruj(nazwa, expresion.ewaluuj(wartNadrzedne));
        }
        catch (BladWyrazenia e){
            e.aktualneWartosciowanie = wartNadrzedne;
            e.blednaInstrukcja = this;
        }
    }
    public void setWyrazenie(Expresion expresion) {
        this.expresion = expresion;
    }

    @Override
    public String toString(){
        if(expresion == null)
            return "DEKLARACJA: " +  nazwa + " = NULL\n";
        else
            return "DEKLARACJA: " +  nazwa + " = " + expresion.toString() + '\n';
    }
}
