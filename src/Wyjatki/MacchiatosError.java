package Wyjatki;

import Instrukcje.Instrukcja;
import Instrukcje.Wartosciowanie;

public class MacchiatosError extends RuntimeException{
    public Instrukcja blednaInstrukcja;
    public Wartosciowanie aktualneWartosciowanie;
    public MacchiatosError(Instrukcja instrukcja, Wartosciowanie aktualneWartosciowanie){
        this.blednaInstrukcja = instrukcja;
        this.aktualneWartosciowanie = aktualneWartosciowanie;
    }
    public MacchiatosError(){
        ;
    }
    public MacchiatosError(String informacja){
        super(informacja);
    }
}
