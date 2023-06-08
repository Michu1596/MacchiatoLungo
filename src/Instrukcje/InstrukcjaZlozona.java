package Instrukcje;

import KlasyPomocnicze.ZakresWidocznosciProcedur;

public abstract class InstrukcjaZlozona extends Instrukcja{
    protected SekwencjaInstrukcji instrukcje;
    protected ZakresWidocznosciProcedur widocznoscProcedur;
    protected InstrukcjaZlozona(){
        instrukcje = new SekwencjaInstrukcji();
    }

    //TODO upewnic sie ze kazda instrukcja zlozona ma dostep do zakresu widocznosci procedur
    // to wymaga odpowiednich builderow
    public InstrukcjaZlozona(InstrukcjaZlozona nadrzedna){
        widocznoscProcedur = nadrzedna.widocznoscProcedur;
    }
    public Procedura getProcedura(String nazwa){
        return widocznoscProcedur.get(nazwa);
    }
    public void dodajInstrukcje(Instrukcja instr){
        instr.wartNadrzedne = wartNadrzedne; //
        instrukcje.dodajInstrukcje(instr);
    }
}
