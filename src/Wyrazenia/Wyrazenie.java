package Wyrazenia;

import Instrukcje.Wartosciowanie;
import Wyjatki.BladWyrazenia;

public interface Wyrazenie {
    int ewaluuj(Wartosciowanie wart) throws BladWyrazenia;

    @Override
    String toString();
}
