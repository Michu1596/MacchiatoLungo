package Tests;

import Builders.BlockBuilder;
import Instructions.*;
import Wyrazenia.Literal;
import Wyrazenia.Variable;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProcedureBuilderTests {
    @Test
    public void declaration(){
        complexInstruction block = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .openProcedure("proc", new char[] {'a', 'b', 'c'})
                .declareVariable('a', new Literal(561))
                .print(new Variable('a'))
                .closeScope()
                .procedureCall("proc", List.of(new Literal(1),
                        new Literal(2),new Literal(1)))
                .getInstruction();

        Block template = new Block();
        template.addDeclaration('x', new Literal(7));
        Procedure proc = new Procedure(template, new char[] {'a', 'b', 'c'});
        proc.addVariable('a', new Literal(561));
        proc.addIntruction(new Print(new Variable('a')));
        template.addProcedure("proc",proc);
        template.addIntruction(new ProcedureCall("proc", List.of(new Literal(1),
                new Literal(2),new Literal(1)), template));
        assertEquals(template.toString(), block.toString());
    }

    @Test
    public void procedureWithoutArgs(){
        complexInstruction block = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .openProcedure("proc")
                .declareVariable('a', new Literal(561))
                .print(new Variable('x'))
                .closeScope()
                .procedureCall("proc")
                .getInstruction();

        Block template = new Block();
        template.addDeclaration('x', new Literal(7));
        Procedure proc = new Procedure(template);
        proc.addVariable('a', new Literal(561));
        proc.addIntruction(new Print(new Variable('x')));
        template.addProcedure("proc",proc);
        template.addIntruction(new ProcedureCall("proc", template));
        assertEquals(template.toString(), block.toString());
    }

    @Test
    public void procedureWithArgs(){
        complexInstruction procedure = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .openProcedure("proc", new char[] {'a', 'b', 'c'})
                .declareVariable('a', new Literal(561))
                .print(new Variable('a'))
                .getInstruction();

        Block template = new Block();
        template.addDeclaration('x', new Literal(7));
        Procedure proc = new Procedure(template, new char[] {'a', 'b', 'c'});
        proc.addVariable('a', new Literal(561));
        proc.addIntruction(new Print(new Variable('a')));
        template.addProcedure("proc",proc);
        template.addIntruction(new ProcedureCall("proc", List.of(new Literal(1),
                new Literal(2),new Literal(1)), template));
        assertEquals(proc.toString(), procedure.toString());
    }
}
