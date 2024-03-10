package Buildery;

import Instrukcje.*;
import Wyrazenia.Expresion;

public class IfBuilder extends Builder{
    protected InstrukcjaWarunkowa instrWar;
    public IfBuilder(Builder zakresZewn, String warunek, Expresion wyr1, Expresion wyr2){
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
        instructionNesting.peek().addIntruction(instrWar);
        instructionNesting.push(instrWar);
    }

    @Override
    public Builder finishScope(){
        instructionNesting.pop();
        return parent;
    }
}
