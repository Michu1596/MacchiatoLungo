package Instrukcje;

public abstract class InstrukcjaZWartosciowaniem extends InstrukcjaZlozona {
    final protected Wartosciowanie wartWewnetrzne;
    protected InstrukcjaZWartosciowaniem(Wartosciowanie nadrzedne){ // do zagniezdzonych blokow
        super(); //tworzy sekwencje instrukcji
        wartWewnetrzne = new Wartosciowanie(nadrzedne);
    }
    protected InstrukcjaZWartosciowaniem(){ //do tworzenia bloku niezagniezdzoneg
        super();
        wartWewnetrzne = new Wartosciowanie();
    }
    @Override
    public void dodajInstrukcje(Instrukcja instr){
        instr.wartNadrzedne = wartWewnetrzne; //
        instr.widocznoscProcedur = widocznoscProcedur;
        instrukcje.dodajInstrukcje(instr);
    }
    @Override
    public Wartosciowanie display(int glebokosc){ // moze null dac
        return wartWewnetrzne.wartosciowanie(glebokosc);
    }
}
