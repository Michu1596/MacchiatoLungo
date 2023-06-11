package Instrukcje;

import KlasyPomocnicze.ZakresWidocznosciProcedur;
import Wyjatki.BladMacchiato;
import Wykonanie.Debugger;
import Wyjatki.PodwojnaDeklaracja;
import Wyrazenia.Wyrazenie;

import java.util.*;

public class Blok extends InstrukcjaZWartosciowaniem{
    private Set<Character> zadklarowaneZmienne;
    protected ZakresWidocznosciProcedur proceduryWewnetrzne;
    public Blok(){
        super(); //tworzy nowe wartosciowanie
        proceduryWewnetrzne = new ZakresWidocznosciProcedur();
        zadklarowaneZmienne = new HashSet<Character>();
    }
    public Blok(InstrukcjaZWartosciowaniem instr){ // blok zagnieżdżony
        super(instr.wartWewnetrzne); //przyslania zmienne
        zadklarowaneZmienne = new HashSet<Character>();
        proceduryWewnetrzne = new ZakresWidocznosciProcedur();
    }

    public Blok(Blok instr){ // blok jako Instrukcja Z Deklaracjami Procedur
        super(instr.wartWewnetrzne); //przyslania zmienne
        proceduryWewnetrzne = new ZakresWidocznosciProcedur(instr.proceduryWewnetrzne);
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

    public void dodajProcedure(String nazwaProcedury, Procedura procedura){
        proceduryWewnetrzne.deklarujProcedure(nazwaProcedury, procedura); //obsluga po2jnej deklaracji znajduje sie
        procedura.widocznoscProcedur = proceduryWewnetrzne;
        // w tej metodzie
    }
    @Override
    public Procedura getProcedura(String nazwa){
        return proceduryWewnetrzne.get(nazwa);
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
    public void dodajInstrukcje(Instrukcja instr){
        instr.wartNadrzedne = wartWewnetrzne; //
        instr.widocznoscProcedur = proceduryWewnetrzne;
        instrukcje.dodajInstrukcje(instr);
    }
    @Override
    public ZakresWidocznosciProcedur getWidocznoscProcedur() {
        return proceduryWewnetrzne;
    }
    @Override
    public String toString(){
        return "BLOK: { " + '\n' + instrukcje.toString() + '}' + '\n';
    }
}
