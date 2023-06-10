package Fabryki;

import Wyrazenia.Dzielenie;
import Wyrazenia.Wyrazenie;

public class DzielenieFAbryka {
    public static Dzielenie dzielenie(Wyrazenie wyr1, Wyrazenie wyr2){
        return new Dzielenie(wyr1, wyr2);
    }
}
