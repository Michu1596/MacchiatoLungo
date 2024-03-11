package Testy;

import Buildery.BlockBuilder;
import Instrukcje.*;
import Wyrazenia.Literal;
import Wyrazenia.Zmienna;
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
                .print(new Zmienna('a'))
                .closeScope()
                .procedureCall("proc", List.of(new Literal(1),
                        new Literal(2),new Literal(1)))
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        Procedura proc = new Procedura(wzor, new char[] {'a', 'b', 'c'});
        proc.dodajDeklaracje('a', new Literal(561));
        proc.addIntruction(new Print(new Zmienna('a')));
        wzor.dodajProcedure("proc",proc);
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
                .print(new Zmienna('x'))
                .closeScope()
                .procedureCall("proc")
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        Procedura proc = new Procedura(wzor);
        proc.dodajDeklaracje('a', new Literal(561));
        proc.addIntruction(new Print(new Zmienna('x')));
        wzor.dodajProcedure("proc",proc);
        wzor.addIntruction(new ProcedureCall("proc", wzor));
        assertEquals(wzor.toString(), blok.toString());
    }

    @Test
    public void proceduraZArgumentami(){
        complexInstruction procedura = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .openProcedure("proc", new char[] {'a', 'b', 'c'})
                .declareVariable('a', new Literal(561))
                .print(new Zmienna('a'))
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        Procedura proc = new Procedura(wzor, new char[] {'a', 'b', 'c'});
        proc.dodajDeklaracje('a', new Literal(561));
        proc.addIntruction(new Print(new Zmienna('a')));
        wzor.dodajProcedure("proc",proc);
        wzor.addIntruction(new ProcedureCall("proc", List.of(new Literal(1),
                new Literal(2),new Literal(1)), wzor));
        assertEquals(proc.toString(), procedura.toString());
    }
}
