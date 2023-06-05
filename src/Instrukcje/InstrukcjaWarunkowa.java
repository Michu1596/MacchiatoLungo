package Instrukcje;

import Wykonanie.Debugger;
import Wyrazenia.Wyrazenie;

public abstract class InstrukcjaWarunkowa extends InstrukcjaZlozona{
    protected Wyrazenie wyr1;
    protected Wyrazenie wyr2;
    protected boolean sprawdzonoWarunek;
    protected boolean warunekZaszedl;
    protected SekwencjaInstrukcji wPrzeciwnymWypadku;
    protected InstrukcjaWarunkowa(){
        super();
    }
    protected InstrukcjaWarunkowa(Wyrazenie wyr1, Wyrazenie wyr2){
        super();
        wPrzeciwnymWypadku = new SekwencjaInstrukcji();
        sprawdzonoWarunek = false;
        this.wyr1 = wyr1;
        this.wyr2 = wyr2;
    }
    protected abstract void sprawdz();

    @Override
    public void wykonaj(){
        sprawdz();
        if(warunekZaszedl)
            instrukcje.wykonaj();
        else
            wPrzeciwnymWypadku.wykonaj();
    }

    @Override
    public Instrukcja nastepnaInstrukcja(){
        if(!sprawdzonoWarunek) {
            sprawdz();
            return new IfSprawdz(this);
        }
        Instrukcja nast;
        if (warunekZaszedl)
            nast = instrukcje.nastepnaInstrukcja();
        else
            nast = wPrzeciwnymWypadku.nastepnaInstrukcja();
        if(nast == null) //jak dojdziemy do konca to zaczyanmy od poczatku
            sprawdzonoWarunek = false;
        return nast;
    }
    @Override
    public InstrukcjaPojedyncza nastepnaInstrukcjaPojedyncza(Debugger debugger){ //TODO poprawic
        if(!sprawdzonoWarunek) {
            sprawdz();
            return new IfSprawdz(this);
        }
        InstrukcjaPojedyncza nast;
        if (warunekZaszedl)
            nast = instrukcje.nastepnaInstrukcjaPojedyncza(debugger);

        else
            nast = wPrzeciwnymWypadku.nastepnaInstrukcjaPojedyncza(debugger);

        if(nast == null) //jak dojdziemy do konca to zaczyanmy od poczatku
            sprawdzonoWarunek = false;
        return nast;
    }
    public void dodajInstrukcjeElse(Instrukcja instr) {
        instr.wartNadrzedne = wartNadrzedne;
        wPrzeciwnymWypadku.dodajInstrukcje(instr);
    }
}
