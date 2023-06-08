package Instrukcje;

import Wykonanie.Debugger;
import Wyrazenia.Wyrazenie;

import java.util.Iterator;
import java.util.List;

public class WywolanieProcedury extends InstrukcjaZlozona{
    protected String nazwaProcedury;
    protected List<Wyrazenie> argumenty;
    protected Procedura procedura;
    protected boolean zainicjalizowano; // uzywane przy debugowaniu. jesli wartosc jest nieustawiona a nastapilo
    // wywolanie to nalezy przekazac procedure argumenty

    /**
     * Instrukcja WywolanieProcedury w momencie tworzenia musi wiedziec czy metoda dostepna jest w jej zakresie
     * @param nazwa nazwa procedury
     * @param argumenty lista wyrazen podanych w kolejnosci wystepowania argumentow
     * @param zakres mowi o tym w zakresie Jakiej innej instrukcji znajduje sie wywowalnie
     */
    public WywolanieProcedury(String nazwa, List<Wyrazenie> argumenty, InstrukcjaZlozona zakres){
        zainicjalizowano = false;
        nazwaProcedury = nazwa;
        procedura = zakres.getProcedura(nazwa);
        this.argumenty = argumenty;
    }
    @Override
    public InstrukcjaPojedyncza nastepnaInstrukcjaPojedyncza(Debugger debugger){
        if(!zainicjalizowano)
            procedura.ustawArgumenty(argumenty);
        Instrukcja nastepnaInst = debugger.getKtoraNastepna();
        InstrukcjaPojedyncza instr = procedura.nastepnaInstrukcjaPojedyncza(debugger);
        if(debugger.getKtoraNastepna() == null) // jesli dojdziemy do konca procedury to nastepna instrukcja to ta ktora
            // znajduje sie po wywolaniu
            debugger.setKtoraNastepna(nastepnaInst);
        if(instr == null) //jesli dojdzeimy do konca wykonania to aczynamy od poczatku
            zainicjalizowano = false;
        return instr;
    }
    @Override
    public Instrukcja nastepnaInstrukcja(){
        return instrukcje.nastepnaInstrukcja();
    }
    @Override
    public void wykonaj(){
        //System.out.println("to tu");
        procedura.ustawArgumenty(argumenty);
        procedura.wykonaj();
    }
    @Override
    public String toString(){
        return nazwaProcedury + "( " + argumenty.toString() + " )";
    }
}
