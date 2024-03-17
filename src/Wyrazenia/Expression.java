package Wyrazenia;

import Instructions.Scope;
import Exceptions.ExpressionError;

public interface Expression {
    int evaluate(Scope scope) throws ExpressionError;

    @Override
    String toString();
}
