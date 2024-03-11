package Factories;

import Wyrazenia.Addition;
import Wyrazenia.Expression;

public class AdditionFactory {
    public static Addition addition(Expression exp1, Expression exp2){
        return new Addition(exp1, exp2);
    }
}
