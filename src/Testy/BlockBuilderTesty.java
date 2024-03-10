package Testy;


import Buildery.BlockBuilder;
import Instrukcje.*;
import Wyrazenia.Literal;
import Wyrazenia.Zmienna;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BlockBuilderTesty {
    @Test
    public void deklaracja(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Zmienna('x'))
                .getInstruction();
        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        wzor.addDeclaration('y', new Zmienna('x'));
        assertEquals(wzor.toString(), blok.toString());
    }
    @Test
    public void blokWewn(){
        complexInstruction blok = new BlockBuilder()
        .declareVariable('x', new Literal(7))
            .beginBlock()
            .declareVariable('y', new Literal(561))
        .finishScope()
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
                .przypisanie('x', new Literal(561))
                .print(new Zmienna('x'))
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        wzor.addIntruction(new Przypisanie('x', new Literal(561)));
        wzor.addIntruction(new Print(new Zmienna('x')));

        assertEquals(wzor.toString(), blok.toString());
    }

    @Test
    public void procedura(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .beginProcedure("proc", new char[] {'a', 'b', 'c'})
                .print(new Zmienna('a'))
                .finishScope()
                .wywolanieProcedury("proc",List.of(new Literal(1),
                        new Literal(2),new Literal(1)))
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        Procedura proc = new Procedura(wzor, new char[] {'a', 'b', 'c'});
        proc.addIntruction(new Print(new Zmienna('a')));
        wzor.dodajProcedure("proc",proc);
        wzor.addIntruction(new WywolanieProcedury("proc", List.of(new Literal(1),
                new Literal(2),new Literal(1)), wzor));
        assertEquals(wzor.toString(), blok.toString());
    }
}
