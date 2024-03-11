package Instrukcje;

import Wyjatki.BladWyrazenia;
import Wyrazenia.Expression;

public class Deklaracja extends InstrukcjaPojedyncza{
    private char nazwa;

    private Expression expression;
    public Deklaracja(char nazwa, Expression expression){
        super();
        this.nazwa = nazwa;
        this.expression = expression;
    }

    public void wykonaj(){
        try {
            wartNadrzedne.deklaruj(nazwa, expression.ewaluuj(wartNadrzedne));
        }
        catch (BladWyrazenia e){
            e.aktualneWartosciowanie = wartNadrzedne;
            e.blednaInstrukcja = this;
        }
    }
    public void setWyrazenie(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString(){
        if(expression == null)
            return "DEKLARACJA: " +  nazwa + " = NULL\n";
        else
            return "DEKLARACJA: " +  nazwa + " = " + expression.toString() + '\n';
    }
}
