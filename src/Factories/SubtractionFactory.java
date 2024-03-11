package Factories;

import Wyrazenia.Subtraction;
import Wyrazenia.Expression;

public class SubtractionFactory {
    public static Subtraction subtraction(Expression exp1, Expression exp2){
        return new Subtraction(exp1, exp2);
    }
}
