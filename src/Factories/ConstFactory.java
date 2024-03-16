package Factories;

import Wyrazenia.Literal;

public class ConstFactory {
    public static Literal wartosc(int liczba){
        return new Literal(liczba);
    }
}
