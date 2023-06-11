package Buildery;

import Instrukcje.Blok;
import Instrukcje.InstrukcjaZlozona;
import Instrukcje.Procedura;
import Wyjatki.BladMacchiato;
import Wyjatki.PodwojnaDeklaracja;
import Wyrazenia.Wyrazenie;

public class BlokBuilder extends Builder{

    protected Blok blok;
    public BlokBuilder(Builder zakresZewnetrzny){
        super(zakresZewnetrzny);

        if(zagniezdzenieWartosciowania.isEmpty())
            this.blok = new Blok();
        else {
            this.blok = new Blok(zagniezdzenieWartosciowania.peek());
        }
        zagniezdzenieInstrukcji.peek().dodajInstrukcje(blok);
        zagniezdzenieInstrukcji.push(blok);
        zagniezdzenieWartosciowania.push(blok);
        zagniezdzenieWidocznosciProcedur.push(blok);
    }

    /**
     * konstruktor do tworzenia bloku zagniezdzonego. Umozliwia on ustawienie zewnetrznego zakresu widocznosci procedur
     * w bloku
     * @param zakresZewnetrzny zewnetrzny zakres widocznosci
     * @param blokZewnetrzny zewnetrzny blok w ktorym zadeklarowany jest nowy blok
     */
    public BlokBuilder(Builder zakresZewnetrzny, Blok blokZewnetrzny){
        super(zakresZewnetrzny);

        if(zagniezdzenieWartosciowania.isEmpty())
            this.blok = new Blok();
        else {
            this.blok = new Blok(zagniezdzenieWartosciowania.peek());
        }
        blok.przypnijBlokZewnetrzny(blokZewnetrzny);
        zagniezdzenieInstrukcji.peek().dodajInstrukcje(blok);
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

        try {
            blok.dodajDeklaracje(nazwa, wyr);
        }catch (PodwojnaDeklaracja e){
            System.out.println("Zmienna: " + nazwa + " zostala juz zadeklarowna w tym bloku");
        }
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

    /**
     * metoda pozwalajaca utworzyc blok zagniezdony
     * @return
     */
    @Override
    public BlokBuilder rozpocznijBlok(){
        return new BlokBuilder(this, blok);
    }

    @Override
    public Builder zamknijZakres(){
        zagniezdzenieInstrukcji.pop();
        zagniezdzenieWartosciowania.pop();
        zagniezdzenieWidocznosciProcedur.pop();
        return nadrzedny;
    }
    @Override
    public InstrukcjaZlozona getInstrukcja() {
        return blok;
    }

}
