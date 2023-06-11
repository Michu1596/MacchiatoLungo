package Buildery;

import Instrukcje.Blok;
import Instrukcje.InstrukcjaZlozona;
import Instrukcje.Procedura;
import Wyjatki.PodwojnaDeklaracja;
import Wyrazenia.Wyrazenie;

public class ProceduraBuilder extends  Builder{ //dziedziczenie wynika stad ze procedura musi byc wewnatrz bloku
    Procedura procedura;
    public ProceduraBuilder(BlokBuilder zakresZewnetrzny, Blok blok, String nazwa, char[] argumenty){
        super(zakresZewnetrzny);
        procedura = new Procedura(blok, argumenty);
        blok.dodajProcedure(nazwa, procedura);
        zagniezdzenieInstrukcji.push(procedura);
        zagniezdzenieWartosciowania.push(procedura);
    }
    public ProceduraBuilder(BlokBuilder zakresZewnetrzny, Blok blok, String nazwa){
        super(zakresZewnetrzny);
        procedura = new Procedura(blok);
        blok.dodajProcedure(nazwa, procedura);
        zagniezdzenieInstrukcji.push(procedura);
        zagniezdzenieWartosciowania.push(procedura);
    }
    public ProceduraBuilder zadeklarujZmienna(char nazwa, Wyrazenie wyr){
        try {
            procedura.dodajDeklaracje(nazwa, wyr);
        }catch (PodwojnaDeklaracja e){
            System.out.println("Zmienna: " + nazwa + " zostala juz zadeklarowana w tej procedurze");
        }
        return this;
    }
    @Override
    public Builder zamknijZakres(){
        zagniezdzenieInstrukcji.pop();
        zagniezdzenieWartosciowania.pop();
        return nadrzedny;
    }
    @Override
    public InstrukcjaZlozona getInstrukcja(){
        return procedura;
    }
}
