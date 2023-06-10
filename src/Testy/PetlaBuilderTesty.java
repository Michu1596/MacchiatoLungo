package Testy;

import Buildery.BlokBuilder;
import Instrukcje.*;
import Wyrazenia.Literal;
import Wyrazenia.Zmienna;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetlaBuilderTesty {
    @Test
    public void test1(){
        InstrukcjaZlozona blok = new BlokBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .przypisanie('x', new Literal(561))
                .rozpocznijPetle('k', new Literal(6))
                .rozpocznijInstrukcjeWarunkowa("==", new Zmienna('x'), new Literal(1))
                .print(new Zmienna('x'))
                .zamknijZakres()
                .zamknijZakres()
                .getInstrukcja();

        Blok wzor = new Blok();
        wzor.dodajDeklaracje('x', new Literal(7));
        wzor.dodajInstrukcje(new Przypisanie('x', new Literal(561)));
        InstrukcjaWarunkowa warunkowa = new IfRowne(new Zmienna('x'), new Literal(1));
        warunkowa.dodajInstrukcje(new Print(new Zmienna('x')));
        PetlaFor petla = new PetlaFor(wzor, 'k', new Literal(6));
        petla.dodajInstrukcje(warunkowa);
        wzor.dodajInstrukcje(petla);

        assertEquals(wzor.toString(), blok.toString());
    }
}
