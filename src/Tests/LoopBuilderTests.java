package Tests;

import Builders.BlockBuilder;
import Instructions.*;
import Wyrazenia.Literal;
import Wyrazenia.Variable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoopBuilderTests {
    @Test
    public void test1(){
        complexInstruction block = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .assignment('x', new Literal(561))
                .openLoopInstruction('k', new Literal(6))
                .openIfInstruction("==", new Variable('x'), new Literal(1))
                .print(new Variable('x'))
                .closeScope()
                .closeScope()
                .getInstruction();

        Block template = new Block();
        template.addDeclaration('x', new Literal(7));
        template.addIntruction(new Assignment('x', new Literal(561)));
        Conditional warunkowa = new CondEqual(new Variable('x'), new Literal(1));
        warunkowa.addIntruction(new Print(new Variable('x')));
        ForLoop loop = new ForLoop(template, 'k', new Literal(6));
        loop.addIntruction(warunkowa);
        template.addIntruction(loop);

        assertEquals(template.toString(), block.toString());
    }
}
