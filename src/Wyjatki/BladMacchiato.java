package Wyjatki;

import Instrukcje.Instrukcja;
import Instrukcje.Wartosciowanie;

public class BladMacchiato extends RuntimeException{
    public Instrukcja blednaInstrukcja;
    public Wartosciowanie aktualneWartosciowanie;
    public BladMacchiato(Instrukcja instrukcja, Wartosciowanie aktualneWartosciowanie){
        this.blednaInstrukcja = instrukcja;
        this.aktualneWartosciowanie = aktualneWartosciowanie;
    }
    public BladMacchiato(){
        ;
    }
    public BladMacchiato(String informacja){
        super(informacja);
    }
}
