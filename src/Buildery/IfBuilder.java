package Buildery;

import Instrukcje.*;
import Wyrazenia.Expression;

public class IfBuilder extends Builder{
    protected InstrukcjaWarunkowa instrWar;
    public IfBuilder(Builder zakresZewn, String warunek, Expression wyr1, Expression wyr2){
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
    public Builder closeScope(){
        instructionNesting.pop();
        return parent;
    }
}
