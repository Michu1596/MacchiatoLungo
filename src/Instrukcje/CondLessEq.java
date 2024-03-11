package Instrukcje;

import Wyrazenia.Expression;

public class CondLessEq extends Conditional {
    public CondLessEq(Expression wyr1, Expression wyr2){
        super(wyr1, wyr2);
    }
    @Override
    protected void sprawdz(){
        sprawdzonoWarunek = true;
        warunekZaszedl = wyr1.ewaluuj(wartNadrzedne) <= wyr2.ewaluuj(wartNadrzedne);
    }

    @Override
    public String toString(){
        return "IF( " + wyr1.toString() + " <= " + wyr2.toString() + " ){" + '\n' + instrukcje.toString()  + "}" + '\n';
    }
}