package Instrukcje;

public class IfSprawdz extends InstrukcjaPojedyncza{
    final protected InstrukcjaWarunkowa doSprawdzenia;
    public IfSprawdz(InstrukcjaWarunkowa instr){
        doSprawdzenia = instr;
    }

    @Override
    public void wykonaj(){
        doSprawdzenia.sprawdz();
    }
    @Override
    public String toString(){
        return doSprawdzenia.toString();
    }
    @Override
    public Wartosciowanie display(int glebokosc){
        return doSprawdzenia.display(glebokosc);
    }
}
