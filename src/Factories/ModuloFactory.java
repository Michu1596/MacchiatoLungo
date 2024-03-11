package Factories;

import Wyrazenia.Modulo;
import Wyrazenia.Expression;

public class ModuloFactory {
    public static Modulo modulo(Expression exp1, Expression exp2){
        return new Modulo(exp1, exp2);
    }
}
