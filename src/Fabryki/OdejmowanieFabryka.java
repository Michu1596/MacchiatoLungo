package Fabryki;

import Wyrazenia.Odejmowanie;
import Wyrazenia.Wyrazenie;

public class OdejmowanieFabryka {
    public static Odejmowanie odejmowanie(Wyrazenie wyr1, Wyrazenie wyr2){
        return new Odejmowanie(wyr1, wyr2);
    }
}
