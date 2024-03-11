package Testy;

import Builders.BlockBuilder;
import Instrukcje.*;
import Wyrazenia.Literal;
import Wyrazenia.Variable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoopBuilderTesty {
    @Test
    public void test1(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .assignment('x', new Literal(561))
                .openLoopInstruction('k', new Literal(6))
                .openIfInstruction("==", new Variable('x'), new Literal(1))
                .print(new Variable('x'))
                .closeScope()
                .closeScope()
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        wzor.addIntruction(new Przypisanie('x', new Literal(561)));
        Conditional warunkowa = new CondEqual(new Variable('x'), new Literal(1));
        warunkowa.addIntruction(new Print(new Variable('x')));
        ForLoop petla = new ForLoop(wzor, 'k', new Literal(6));
        petla.addIntruction(warunkowa);
        wzor.addIntruction(petla);

        assertEquals(wzor.toString(), blok.toString());
    }
}
