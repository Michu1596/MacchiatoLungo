package Testy;

import Builders.BlockBuilder;
import Instructions.*;
import Wyrazenia.Literal;
import Wyrazenia.Variable;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProcedureBuilderTesty {
    @Test
    public void deklaracja(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .openProcedure("proc", new char[] {'a', 'b', 'c'})
                .declareVariable('a', new Literal(561))
                .print(new Variable('a'))
                .closeScope()
                .procedureCall("proc", List.of(new Literal(1),
                        new Literal(2),new Literal(1)))
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        Procedure proc = new Procedure(wzor, new char[] {'a', 'b', 'c'});
        proc.addVariable('a', new Literal(561));
        proc.addIntruction(new Print(new Variable('a')));
        wzor.addProcedure("proc",proc);
        wzor.addIntruction(new ProcedureCall("proc", List.of(new Literal(1),
                new Literal(2),new Literal(1)), wzor));
        assertEquals(wzor.toString(), blok.toString());
    }

    @Test
    public void proceduraBezArgumentow(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .openProcedure("proc")
                .declareVariable('a', new Literal(561))
                .print(new Variable('x'))
                .closeScope()
                .procedureCall("proc")
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        Procedure proc = new Procedure(wzor);
        proc.addVariable('a', new Literal(561));
        proc.addIntruction(new Print(new Variable('x')));
        wzor.addProcedure("proc",proc);
        wzor.addIntruction(new ProcedureCall("proc", wzor));
        assertEquals(wzor.toString(), blok.toString());
    }

    @Test
    public void proceduraZArgumentami(){
        complexInstruction procedura = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .openProcedure("proc", new char[] {'a', 'b', 'c'})
                .declareVariable('a', new Literal(561))
                .print(new Variable('a'))
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        Procedure proc = new Procedure(wzor, new char[] {'a', 'b', 'c'});
        proc.addVariable('a', new Literal(561));
        proc.addIntruction(new Print(new Variable('a')));
        wzor.addProcedure("proc",proc);
        wzor.addIntruction(new ProcedureCall("proc", List.of(new Literal(1),
                new Literal(2),new Literal(1)), wzor));
        assertEquals(proc.toString(), procedura.toString());
    }
}
