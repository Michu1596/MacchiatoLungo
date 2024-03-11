package Instrukcje;

import KlasyPomocnicze.ZakresWidocznosciProcedur;
import Wykonanie.Debugger;
import Wyjatki.DoubleDeclaration;
import Wyrazenia.Expression;

import java.util.*;

public class Block extends InstrukcjaZWartosciowaniem{
    private Set<Character> zadklarowaneZmienne;
    protected ZakresWidocznosciProcedur proceduryWewnetrzne;
    public Block(){
        super(); //tworzy nowe wartosciowanie
        proceduryWewnetrzne = new ZakresWidocznosciProcedur();
        zadklarowaneZmienne = new HashSet<>();
    }
    public Block(InstrukcjaZWartosciowaniem instr){ // blok zagnieżdżony
        super(instr.wartWewnetrzne); //przyslania zmienne
        zadklarowaneZmienne = new HashSet<>();
        proceduryWewnetrzne = new ZakresWidocznosciProcedur();
    }

    //metoda potrzebna do tworzenia bloku bezposrednio zagniezdzonego w innym bloku
    public void connectOuterBlock(Block instr){ // blok jako Instrukcja Z Deklaracjami Procedur
        proceduryWewnetrzne = new ZakresWidocznosciProcedur(instr.proceduryWewnetrzne);
    }
    public void addDeclaration(char zmienna, Expression wartosc){
        if(zadklarowaneZmienne.contains(zmienna))
            throw new DoubleDeclaration(zmienna);
        zadklarowaneZmienne.add(zmienna);
        Deklaracja dekl = new Deklaracja(zmienna, wartosc);
        dekl.wartNadrzedne = wartWewnetrzne;
        instrukcje.dodajInstrukcje(dekl);
    }

    public void dodajProcedure(String nazwaProcedury, Procedura procedura){
        proceduryWewnetrzne.deklarujProcedure(nazwaProcedury, procedura); //obsluga po2jnej deklaracji znajduje sie
        // w tej metodzie
        procedura.widocznoscProcedur = proceduryWewnetrzne;
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
    public void addIntruction(Instrukcja instr){
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
