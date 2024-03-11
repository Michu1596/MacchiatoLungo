package Instrukcje;

public abstract class complexInstruction extends Instrukcja{
    protected SekwencjaInstrukcji instrukcje;

    protected complexInstruction(){
        instrukcje = new SekwencjaInstrukcji();
    }

    /*public InstrukcjaZlozona(InstrukcjaZlozona nadrzedna){
        widocznoscProcedur = nadrzedna.widocznoscProcedur;
    }*/
    public Procedure getProcedura(String nazwa){
        return widocznoscProcedur.get(nazwa);
    }
    public void addIntruction(Instrukcja instr){
        instr.wartNadrzedne = wartNadrzedne;
        instr.widocznoscProcedur = widocznoscProcedur;
        instrukcje.dodajInstrukcje(instr);
    }
}
