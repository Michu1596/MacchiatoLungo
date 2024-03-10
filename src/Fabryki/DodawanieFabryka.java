package Fabryki;

import Wyrazenia.Dodawanie;
import Wyrazenia.Expresion;

public class DodawanieFabryka {
    public static Dodawanie dodawanie(Expresion wyr1, Expresion wyr2){
        return new Dodawanie(wyr1, wyr2);
    }
}
