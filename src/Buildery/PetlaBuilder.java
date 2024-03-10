package Buildery;

import Instrukcje.PetlaFor;
import Wyrazenia.Expresion;

/**
 * petla musi znajdowac sie wewnatrz jakiegos bloku
 */
public class PetlaBuilder extends Builder{
    protected PetlaFor petla;
    public PetlaBuilder(Builder zakresZewnetrzny, char zmienna, Expresion wyr){
        super(zakresZewnetrzny);
        petla = new PetlaFor(scopesNesting.peek(), zmienna, wyr);
        instructionNesting.peek().addIntruction(petla);
        instructionNesting.push(petla);
        scopesNesting.push(petla);
    }

    @Override
    public Builder finishScope() {
        scopesNesting.pop();
        instructionNesting.pop();
        return parent;
    }
}
