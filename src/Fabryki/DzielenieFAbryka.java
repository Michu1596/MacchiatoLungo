package Fabryki;

import Wyrazenia.Dzielenie;
import Wyrazenia.Expression;

public class DzielenieFAbryka {
    public static Dzielenie dzielenie(Expression wyr1, Expression wyr2){
        return new Dzielenie(wyr1, wyr2);
    }
}
