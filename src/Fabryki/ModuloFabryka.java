package Fabryki;

import Wyrazenia.Modulo;
import Wyrazenia.Wyrazenie;

public class ModuloFabryka {
    public static Modulo modulo(Wyrazenie wyr1, Wyrazenie wyr2){
        return new Modulo(wyr1, wyr2);
    }
}
