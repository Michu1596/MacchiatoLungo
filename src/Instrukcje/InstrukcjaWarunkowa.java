package Instrukcje;

import Wykonanie.Debugger;
import Wyrazenia.Expression;

public abstract class InstrukcjaWarunkowa extends complexInstruction {
    protected Expression wyr1;
    protected Expression wyr2;
    protected boolean sprawdzonoWarunek;
    protected boolean warunekZaszedl;
    protected SekwencjaInstrukcji wPrzeciwnymWypadku;
    protected InstrukcjaWarunkowa(){
        super();
    }
    protected InstrukcjaWarunkowa(Expression wyr1, Expression wyr2){
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
    public InstrukcjaPojedyncza nastepnaInstrukcjaPojedyncza(Debugger debugger){
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
