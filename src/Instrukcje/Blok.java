package Instrukcje;

import Wykonanie.Debugger;
import Wyjatki.PodwojnaDeklaracja;
import Wyrazenia.Wyrazenie;

import java.util.*;

public class Blok extends InstrukcjaZWartosciowaniem{
    private Set<Character> zadklarowaneZmienne;
    public Blok(){
        super(); //tworzy nowe wartosciowanie
        zadklarowaneZmienne = new HashSet<Character>();
    }
    public Blok(InstrukcjaZWartosciowaniem instr){ // blok zagnieżdżony
        super(instr.wartWewnetrzne); //przyslania zmienne
        zadklarowaneZmienne = new HashSet<Character>();
    }
    public void dodajDeklaracje(char zmienna, Wyrazenie wartosc){
        if(zadklarowaneZmienne.contains(zmienna))
            throw new PodwojnaDeklaracja(zmienna);
        zadklarowaneZmienne.add(zmienna);
        Deklaracja dekl = new Deklaracja(zmienna, wartosc);
        dekl.wartNadrzedne = wartWewnetrzne;
        instrukcje.dodajInstrukcje(dekl);
    }

    @Override
    public void wykonaj() {
        instrukcje.wykonaj();
    }

    @Override
    public Instrukcja nastepnaInstrukcja(){
        return instrukcje.nastepnaInstrukcja();
    }

    @Override
    public InstrukcjaPojedyncza nastepnaInstrukcjaPojedyncza(Debugger debugger){
        return instrukcje.nastepnaInstrukcjaPojedyncza(debugger);
    }

    @Override
    public String toString(){
        return "BLOK: { " + '\n' + instrukcje.toString() + '}' + '\n';
    }
}
