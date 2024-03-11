package Buildery;

import Instrukcje.PetlaFor;
import Wyrazenia.Expression;

/**
 * petla musi znajdowac sie wewnatrz jakiegos bloku
 */
public class LoopBuilder extends Builder{
    protected PetlaFor petla;
    public LoopBuilder(Builder zakresZewnetrzny, char zmienna, Expression wyr){
        super(zakresZewnetrzny);
        petla = new PetlaFor(scopesNesting.peek(), zmienna, wyr);
        instructionNesting.peek().addIntruction(petla);
        instructionNesting.push(petla);
        scopesNesting.push(petla);
    }

    @Override
    public Builder closeScope() {
        scopesNesting.pop();
        instructionNesting.pop();
        return parent;
    }
}
