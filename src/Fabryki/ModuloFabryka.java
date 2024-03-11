package Fabryki;

import Wyrazenia.Modulo;
import Wyrazenia.Expression;

public class ModuloFabryka {
    public static Modulo modulo(Expression wyr1, Expression wyr2){
        return new Modulo(wyr1, wyr2);
    }
}
