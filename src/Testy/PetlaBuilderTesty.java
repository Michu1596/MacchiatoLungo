package Testy;

import Buildery.BlockBuilder;
import Instrukcje.*;
import Wyrazenia.Literal;
import Wyrazenia.Zmienna;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetlaBuilderTesty {
    @Test
    public void test1(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .przypisanie('x', new Literal(561))
                .rozpocznijPetle('k', new Literal(6))
                .rozpocznijInstrukcjeWarunkowa("==", new Zmienna('x'), new Literal(1))
                .print(new Zmienna('x'))
                .finishScope()
                .finishScope()
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        wzor.addIntruction(new Przypisanie('x', new Literal(561)));
        InstrukcjaWarunkowa warunkowa = new IfRowne(new Zmienna('x'), new Literal(1));
        warunkowa.addIntruction(new Print(new Zmienna('x')));
        PetlaFor petla = new PetlaFor(wzor, 'k', new Literal(6));
        petla.addIntruction(warunkowa);
        wzor.addIntruction(petla);

        assertEquals(wzor.toString(), blok.toString());
    }
}
