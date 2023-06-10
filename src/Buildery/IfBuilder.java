package Buildery;

import Instrukcje.*;
import Wyrazenia.Wyrazenie;

public class IfBuilder extends Builder{
    protected InstrukcjaWarunkowa instrWar;
    public IfBuilder(Builder zakresZewn, String warunek, Wyrazenie wyr1, Wyrazenie wyr2){
        super(zakresZewn);
        switch (warunek){
            case "<":
                instrWar = new IfMniejsze(wyr1, wyr2);
                break;
            case "==":
                instrWar = new IfRowne(wyr1, wyr2);
                break;
            case ">":
                instrWar = new IfWieksze(wyr1, wyr2);
                break;
            case "<=":
                instrWar = new IfMniejszeRowne(wyr1, wyr2);
                break;
            case ">=":
                instrWar = new IfWiekszeRowne(wyr1, wyr2);
                break;
        }
        zagniezdzenieInstrukcji.peek().dodajInstrukcje(instrWar);
        zagniezdzenieInstrukcji.push(instrWar);
    }

    @Override
    public Builder zamknijZakres(){
        zagniezdzenieInstrukcji.pop();
        return nadrzedny;
    }
}
