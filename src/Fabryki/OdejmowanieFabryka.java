package Fabryki;

import Wyrazenia.Odejmowanie;
import Wyrazenia.Expresion;

public class OdejmowanieFabryka {
    public static Odejmowanie odejmowanie(Expresion wyr1, Expresion wyr2){
        return new Odejmowanie(wyr1, wyr2);
    }
}
