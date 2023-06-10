package Fabryki;

import Wyrazenia.Dodawanie;
import Wyrazenia.Wyrazenie;

public class DodawanieFabryka {
    public static Dodawanie dodawanie(Wyrazenie wyr1, Wyrazenie wyr2){
        return new Dodawanie(wyr1, wyr2);
    }
}
