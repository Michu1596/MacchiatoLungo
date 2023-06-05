package Instrukcje;

import Wyrazenia.Wyrazenie;

public class IfWieksze extends InstrukcjaWarunkowa{
    public IfWieksze(Wyrazenie wyr1, Wyrazenie wyr2){
        super(wyr1, wyr2);
    }
    @Override
    protected void sprawdz(){
        sprawdzonoWarunek = true;
        warunekZaszedl = wyr1.ewaluuj(wartNadrzedne) > wyr2.ewaluuj(wartNadrzedne);
    }

    @Override
    public String toString(){
        return "IF( " + wyr1.toString() + " > " + wyr2.toString() + " ){" + '\n' + instrukcje.toString() + "}" + '\n';
    }
}
