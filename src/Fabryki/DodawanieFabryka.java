package Fabryki;

import Wyrazenia.Dodawanie;
import Wyrazenia.Expression;

public class DodawanieFabryka {
    public static Dodawanie dodawanie(Expression wyr1, Expression wyr2){
        return new Dodawanie(wyr1, wyr2);
    }
}
