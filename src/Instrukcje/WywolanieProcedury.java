package Instrukcje;


import Wyjatki.NieprawidloweArgumentyProcedury;
import Wykonanie.Debugger;
import Wyrazenia.Literal;
import Wyrazenia.Wyrazenie;

import java.util.ArrayList;

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
        if (procedura.getLiczbaPArametrow() != argumenty.size())
            throw new NieprawidloweArgumentyProcedury();
        this.argumenty = argumenty;
    }
    public WywolanieProcedury(String nazwa, InstrukcjaZlozona zakres){
        zainicjalizowano = false;
        nazwaProcedury = nazwa;
        procedura = zakres.getProcedura(nazwa);
        if (procedura.getLiczbaPArametrow() != 0)
            throw new NieprawidloweArgumentyProcedury();
    }

    /**
     * metoda dajaca dostep do nastepnej instrukcji w procedurze. W momencie wywolania inicjalizuje wartosci argumentow
     * procedury. Wylicza ich wartosci i tworzy liste Literalow ktora zostaje przekazana procedurze. Dzieje sie tak
     * poniewaz chcemy przekazac procedurze argumenty przez wartosc i potrzebujemy mechanizmu wymuszajacego ich
     * kopiowanie.
     * @param debugger
     * @return
     */

    @Override
    public InstrukcjaPojedyncza nastepnaInstrukcjaPojedyncza(Debugger debugger){
        if(!zainicjalizowano){
            List<Wyrazenie> wartosciWyrazen = new ArrayList<>();
            for(Wyrazenie wyrazenie : argumenty) // tworzy liste literalow zawierajacych wartosci wyrazen obliczone
                                                 // w miejscu wywolania procedury
                wartosciWyrazen.add(new Literal(wyrazenie.ewaluuj(wartNadrzedne)));
            procedura.ustawArgumenty(wartosciWyrazen);
        }
        Instrukcja nastepnaInst = debugger.getKtoraNastepna();
        InstrukcjaPojedyncza instr = procedura.nastepnaInstrukcjaPojedyncza(debugger);
        if(debugger.getKtoraNastepna() == null) // jesli dojdziemy do konca procedury to nastepna instrukcja to ta ktora
                                                // znajduje sie po wywolaniu
            debugger.setKtoraNastepna(nastepnaInst);
        if(instr == null)                       //jesli dojdzeimy do konca wykonania to aczynamy od poczatku
            zainicjalizowano = false;
        return instr;
    }
    @Override
    public Instrukcja nastepnaInstrukcja(){
        return instrukcje.nastepnaInstrukcja();
    }
    @Override
    public void wykonaj(){
        procedura.ustawArgumenty(argumenty);
        procedura.wykonaj();
    }
    @Override
    public String toString(){
        if (argumenty != null)
            return nazwaProcedury + "( " + argumenty.toString() + " )";
        else
            return nazwaProcedury + "()\n";
    }
}
