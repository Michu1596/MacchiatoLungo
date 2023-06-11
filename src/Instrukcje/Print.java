package Instrukcje;

import Wyrazenia.Wyrazenie;

public class Print extends InstrukcjaPojedyncza{
    private Wyrazenie wyr1;
    public Print(Wyrazenie wyr1){
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
