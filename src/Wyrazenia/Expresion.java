package Wyrazenia;

import Instrukcje.Wartosciowanie;
import Wyjatki.BladWyrazenia;

public interface Expresion {
    int ewaluuj(Wartosciowanie wart) throws BladWyrazenia;

    @Override
    String toString();
}
