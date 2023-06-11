package Instrukcje;

import KlasyPomocnicze.ZakresWidocznosciProcedur;

public abstract class InstrukcjaZlozona extends Instrukcja{
    protected SekwencjaInstrukcji instrukcje;

    protected InstrukcjaZlozona(){
        instrukcje = new SekwencjaInstrukcji();
    }

    /*public InstrukcjaZlozona(InstrukcjaZlozona nadrzedna){
        widocznoscProcedur = nadrzedna.widocznoscProcedur;
    }*/
    public Procedura getProcedura(String nazwa){
        return widocznoscProcedur.get(nazwa);
    }
    public void dodajInstrukcje(Instrukcja instr){
        instr.wartNadrzedne = wartNadrzedne;
        instr.widocznoscProcedur = widocznoscProcedur;
        instrukcje.dodajInstrukcje(instr);
    }
}
