package Instrukcje;

import Wykonanie.Debugger;

public abstract class InstrukcjaPojedyncza extends Instrukcja{ // jej wykoannie liczymy w debuggerze
    protected boolean wykonano; //naprzemian daje wartosc i null
    protected InstrukcjaPojedyncza(){
        wykonano = false;
    }
    @Override
    public InstrukcjaPojedyncza nastepnaInstrukcjaPojedyncza(Debugger debugger){
        if(wykonano){
            wykonano = false;
            return null;
        }
        wykonano = true;
        return this;
    }
    @Override
    public Instrukcja nastepnaInstrukcja(){
        return this;
    }
}
