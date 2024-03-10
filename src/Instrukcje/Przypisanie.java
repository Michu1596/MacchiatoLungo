package Instrukcje;

import Wyjatki.BladMacchiato;
import Wyrazenia.Expresion;

public class Przypisanie extends InstrukcjaPojedyncza{
    final private char nazwa;
    private Expresion expresion;
    public Przypisanie(char nazwa, Expresion expresion){
        super();
        this.nazwa = nazwa;
        this.expresion = expresion;
    }

    @Override
    public void wykonaj(){
        try {
            wartNadrzedne.set(nazwa, expresion.ewaluuj(wartNadrzedne));
        }
        catch (BladMacchiato e){
            e.blednaInstrukcja = this;
            e.aktualneWartosciowanie = wartNadrzedne;
            throw e;
        }
    }

    @Override
    public String toString(){
        return "PRZYPISANIE: " + nazwa + " = " + expresion.toString() + '\n';
    }

}
