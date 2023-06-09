package Buildery;

import Instrukcje.PetlaFor;
import Wyrazenia.Wyrazenie;

/**
 * petla musi znajdowac sie wewnatrz jakiegos bloku
 */
public class PetlaBuilder extends Builder{
    protected PetlaFor petla;
    public PetlaBuilder(Builder zakresZewnetrzny, char zmienna, Wyrazenie wyr){
        super(zakresZewnetrzny);
        petla = new PetlaFor(zagniezdzenieWartosciowania.peek(), zmienna, wyr);
        zagniezdzenieInstrukcji.push(petla);
        zagniezdzenieWartosciowania.push(petla);
    }

    @Override
    public Builder zamknijZakres() {
        zagniezdzenieWartosciowania.pop();
        zagniezdzenieInstrukcji.pop();
        return nadrzedny;
    }
}
