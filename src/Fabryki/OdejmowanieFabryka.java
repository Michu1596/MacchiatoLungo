package Fabryki;

import Wyrazenia.Odejmowanie;
import Wyrazenia.Expression;

public class OdejmowanieFabryka {
    public static Odejmowanie odejmowanie(Expression wyr1, Expression wyr2){
        return new Odejmowanie(wyr1, wyr2);
    }
}
