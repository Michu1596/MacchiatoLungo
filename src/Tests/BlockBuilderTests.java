package Tests;


import Builders.BlockBuilder;
import Instructions.*;
import Wyrazenia.Literal;
import Wyrazenia.Variable;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BlockBuilderTests {
    @Test
    public void declaration(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Variable('x'))
                .getInstruction();
        Block template = new Block();
        template.addDeclaration('x', new Literal(7));
        template.addDeclaration('y', new Variable('x'));
        assertEquals(template.toString(), blok.toString());
    }
    @Test
    public void innerBlock(){
        complexInstruction block = new BlockBuilder()
        .declareVariable('x', new Literal(7))
            .openBlock()
            .declareVariable('y', new Literal(561))
        .closeScope()
        .getInstruction();

        Block template = new Block();
        template.addDeclaration('x', new Literal(7));
        Block innerBlock = new Block(template);
        innerBlock.addDeclaration('y', new Literal(561));
        template.addIntruction(innerBlock);

        assertEquals(template.toString(), block.toString());
    }
    @Test
    public void singleInstructions(){
        complexInstruction block = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .assignment('x', new Literal(561))
                .print(new Variable('x'))
                .getInstruction();

        Block template = new Block();
        template.addDeclaration('x', new Literal(7));
        template.addIntruction(new Assignment('x', new Literal(561)));
        template.addIntruction(new Print(new Variable('x')));

        assertEquals(template.toString(), block.toString());
    }

    @Test
    public void procedura(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .openProcedure("proc", new char[] {'a', 'b', 'c'})
                .print(new Variable('a'))
                .closeScope()
                .procedureCall("proc",List.of(new Literal(1),
                        new Literal(2),new Literal(1)))
                .getInstruction();

        Block template = new Block();
        template.addDeclaration('x', new Literal(7));
        Procedure proc = new Procedure(template, new char[] {'a', 'b', 'c'});
        proc.addIntruction(new Print(new Variable('a')));
        template.addProcedure("proc",proc);
        template.addIntruction(new ProcedureCall("proc", List.of(new Literal(1),
                new Literal(2),new Literal(1)), template));
        assertEquals(template.toString(), blok.toString());
    }
}
