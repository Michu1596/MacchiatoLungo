package Instrukcje;

import Wyjatki.BladMacchiato;
import Wyrazenia.Wyrazenie;

public class Przypisanie extends InstrukcjaPojedyncza{
    final private char nazwa;
    private Wyrazenie wyrazenie;
    public Przypisanie(char nazwa, Wyrazenie wyrazenie){
        super();
        this.nazwa = nazwa;
        this.wyrazenie = wyrazenie;
    }

    @Override
    public void wykonaj(){
        try {
            wartNadrzedne.set(nazwa, wyrazenie.ewaluuj(wartNadrzedne));
        }
        catch (BladMacchiato e){
            e.blednaInstrukcja = this;
            e.aktualneWartosciowanie = wartNadrzedne;
            throw e;
        }
    }

    @Override
    public String toString(){
        return "PRZYPISANIE: " + nazwa + " = " + wyrazenie.toString() + '\n';
    }

}
