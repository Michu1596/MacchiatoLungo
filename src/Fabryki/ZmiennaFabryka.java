package Fabryki;

import Wyrazenia.Zmienna;

public class ZmiennaFabryka {
    public static Zmienna nazwa(char nazwa){
        return new Zmienna(nazwa);
    }
}
