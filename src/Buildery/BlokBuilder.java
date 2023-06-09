package Buildery;

import Instrukcje.Blok;
import Instrukcje.Procedura;
import Wyrazenia.Wyrazenie;

public class BlokBuilder extends Builder{
    protected Blok blok;
    public BlokBuilder(Builder zakresZewnetrzny){
        super(zakresZewnetrzny);

        if(zagniezdzenieWartosciowania.isEmpty())
            this.blok = new Blok();
        else
            this.blok = new Blok(zagniezdzenieWartosciowania.peek());

        zagniezdzenieInstrukcji.push(blok);
        zagniezdzenieWartosciowania.push(blok);
        zagniezdzenieWidocznosciProcedur.push(blok);
    }

    public BlokBuilder(){
        super();
        this.blok = new Blok();
        zagniezdzenieInstrukcji.push(blok);
        zagniezdzenieWartosciowania.push(blok);
        zagniezdzenieWidocznosciProcedur.push(blok);
    }
    @Override
    public BlokBuilder zadeklarujZmienna(char nazwa, Wyrazenie wyr){
        blok.dodajDeklaracje(nazwa, wyr);
        return this;
    }

    @Override
    public ProceduraBuilder rozpocznijProcedure(String nazwa, char[] argumenty){
        return new ProceduraBuilder(this, blok, nazwa, argumenty);
    }
    //w wersji bezargumentowej
    @Override
    public ProceduraBuilder rozpocznijProcedure(String nazwa){
        return new ProceduraBuilder(this, blok, nazwa);
    }

    @Override
    public Builder zamknijZakres(){
        zagniezdzenieInstrukcji.pop();
        zagniezdzenieWartosciowania.pop();
        zagniezdzenieWidocznosciProcedur.pop();
        return nadrzedny;
    }

    @Override
    public Blok zbuduj(){
        return blok;
    }

}
