package Wyrazenia;

import Instructions.Scope;
import Exceptions.ExpressionError;

public interface Expression {
    int evaluate(Scope wart) throws ExpressionError;

    @Override
    String toString();
}
