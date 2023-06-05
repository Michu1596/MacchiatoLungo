package Instrukcje;

import Wykonanie.Debugger;

public abstract class Instrukcja{
    protected Wartosciowanie wartNadrzedne;
    public abstract void wykonaj();
    public abstract Instrukcja nastepnaInstrukcja();
    public abstract InstrukcjaPojedyncza nastepnaInstrukcjaPojedyncza(Debugger debugger);
    @Override
    public abstract String toString();
    //dispaly jest nadpisane w klasie InstrukcjaZWartosciowaniem
    public Wartosciowanie display(int glebokosc){ // moze null dac
        return wartNadrzedne.wartosciowanie(glebokosc);
    }
}
