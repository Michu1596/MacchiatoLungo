package Instrukcje;

import Wyjatki.MacchiatosError;
import Wyrazenia.Expression;

public class Przypisanie extends InstrukcjaPojedyncza{
    final private char nazwa;
    private Expression expression;
    public Przypisanie(char nazwa, Expression expression){
        super();
        this.nazwa = nazwa;
        this.expression = expression;
    }

    @Override
    public void wykonaj(){
        try {
            wartNadrzedne.set(nazwa, expression.ewaluuj(wartNadrzedne));
        }
        catch (MacchiatosError e){
            e.blednaInstrukcja = this;
            e.aktualneWartosciowanie = wartNadrzedne;
            throw e;
        }
    }

    @Override
    public String toString(){
        return "PRZYPISANIE: " + nazwa + " = " + expression.toString() + '\n';
    }

}
