package Instrukcje;

import Wyrazenia.Expression;

public class Print extends InstrukcjaPojedyncza{
    private Expression wyr1;
    public Print(Expression wyr1){
        super();
        this.wyr1 = wyr1;
    }
    @Override
    public void wykonaj(){
        System.out.println(wyr1.ewaluuj(wartNadrzedne));
    }
    @Override
    public String toString(){
        return "PRINT( " + wyr1.toString() + " )" + '\n';
    }
}
