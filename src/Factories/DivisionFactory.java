package Factories;

import Wyrazenia.Division;
import Wyrazenia.Expression;

public class DivisionFactory {
    public static Division division(Expression exp1, Expression exp2){
        return new Division(exp1, exp2);
    }
}
