package Testy;


import Builders.BlockBuilder;
import Instructions.*;
import Wyrazenia.Literal;
import Wyrazenia.Variable;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BlockBuilderTesty {
    @Test
    public void deklaracja(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Variable('x'))
                .getInstruction();
        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        wzor.addDeclaration('y', new Variable('x'));
        assertEquals(wzor.toString(), blok.toString());
    }
    @Test
    public void blokWewn(){
        complexInstruction blok = new BlockBuilder()
        .declareVariable('x', new Literal(7))
            .openBlock()
            .declareVariable('y', new Literal(561))
        .closeScope()
        .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        Block wnetrze = new Block(wzor);
        wnetrze.addDeclaration('y', new Literal(561));
        wzor.addIntruction(wnetrze);

        assertEquals(wzor.toString(), blok.toString());
    }
    @Test
    public void instrukcjePojedyncze(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .assignment('x', new Literal(561))
                .print(new Variable('x'))
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        wzor.addIntruction(new Przypisanie('x', new Literal(561)));
        wzor.addIntruction(new Print(new Variable('x')));

        assertEquals(wzor.toString(), blok.toString());
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

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        Procedure proc = new Procedure(wzor, new char[] {'a', 'b', 'c'});
        proc.addIntruction(new Print(new Variable('a')));
        wzor.addProcedure("proc",proc);
        wzor.addIntruction(new ProcedureCall("proc", List.of(new Literal(1),
                new Literal(2),new Literal(1)), wzor));
        assertEquals(wzor.toString(), blok.toString());
    }
}
