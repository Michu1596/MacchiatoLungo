package Wyrazenia;

import Instructions.Scope;
import Wyjatki.ExpressionError;

public interface Expression {
    int evaluate(Scope wart) throws ExpressionError;

    @Override
    String toString();
}
