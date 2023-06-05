package Instrukcje;

import Wyjatki.BladWyrazenia;
import Wyrazenia.Wyrazenie;

public class Deklaracja extends InstrukcjaPojedyncza{
    private char nazwa;
    private Wyrazenie wyrazenie;
    public Deklaracja(char nazwa, Wyrazenie wyrazenie){
        super();
        this.nazwa = nazwa;
        this.wyrazenie = wyrazenie;
    }

    public void wykonaj(){
        try {
            wartNadrzedne.deklaruj(nazwa, wyrazenie.ewaluuj(wartNadrzedne));
        }
        catch (BladWyrazenia e){
            e.aktualneWartosciowanie = wartNadrzedne;
            e.blednaInstrukcja = this;
        }
    }

    @Override
    public String toString(){
        return "DEKLARACJA: " +  nazwa + " = " + wyrazenie.toString() + '\n';
    }
}
