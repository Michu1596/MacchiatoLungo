package Instrukcje;

public abstract class InstrukcjaZlozona extends Instrukcja{
    protected SekwencjaInstrukcji instrukcje;
    protected InstrukcjaZlozona(){
        instrukcje = new SekwencjaInstrukcji();
    }
    public void dodajInstrukcje(Instrukcja instr){
        instr.wartNadrzedne = wartNadrzedne; //
        instrukcje.dodajInstrukcje(instr);
    }
}
