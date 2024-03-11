package Wyrazenia;

import Instrukcje.Wartosciowanie;
import Wyjatki.BladWyrazenia;

public interface Expression {
    int ewaluuj(Wartosciowanie wart) throws BladWyrazenia;

    @Override
    String toString();
}
