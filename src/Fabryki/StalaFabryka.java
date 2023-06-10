package Fabryki;

import Wyrazenia.Literal;

public class StalaFabryka {
    public static Literal wartosc(int liczba){
        return new Literal(liczba);
    }
}
