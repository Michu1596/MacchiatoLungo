package Testy;

import Buildery.BlockBuilder;
import Instrukcje.*;
import Wyrazenia.Literal;
import Wyrazenia.Zmienna;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoopBuilderTesty {
    @Test
    public void test1(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .assignment('x', new Literal(561))
                .openLoopInstruction('k', new Literal(6))
                .openIfInstruction("==", new Zmienna('x'), new Literal(1))
                .print(new Zmienna('x'))
                .closeScope()
                .closeScope()
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
