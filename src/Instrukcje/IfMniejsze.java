package Instrukcje;

import Wyrazenia.Expresion;

public class IfMniejsze extends  InstrukcjaWarunkowa{
    public IfMniejsze(Expresion wyr1, Expresion wyr2){
        super(wyr1, wyr2);
    }
    @Override
    protected void sprawdz(){
        sprawdzonoWarunek = true;
        warunekZaszedl = wyr1.ewaluuj(wartNadrzedne) < wyr2.ewaluuj(wartNadrzedne);
    }

    @Override
    public String toString(){
        return "IF( " + wyr1.toString() + " < " + wyr2.toString() + " ){" + '\n' + instrukcje.toString()  + "}" + '\n';
    }
}
