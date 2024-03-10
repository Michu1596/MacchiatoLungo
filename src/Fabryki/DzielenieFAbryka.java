package Fabryki;

import Wyrazenia.Dzielenie;
import Wyrazenia.Expresion;

public class DzielenieFAbryka {
    public static Dzielenie dzielenie(Expresion wyr1, Expresion wyr2){
        return new Dzielenie(wyr1, wyr2);
    }
}
