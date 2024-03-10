package Fabryki;

import Wyrazenia.Modulo;
import Wyrazenia.Expresion;

public class ModuloFabryka {
    public static Modulo modulo(Expresion wyr1, Expresion wyr2){
        return new Modulo(wyr1, wyr2);
    }
}
