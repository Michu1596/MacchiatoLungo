package Testy;

import Instructions.Block;
import Instructions.Print;
import Instructions.Procedure;
import Instructions.ProcedureCall;
import Wyjatki.DoubleDeclaration;
import Wyrazenia.Literal;
import Wyrazenia.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testy klasy Procedura
 */
public class ProcedureTesty {
    protected Block block;
    char[] arg;
    @BeforeEach
    public void blok(){
        block = new Block();
        arg = new char[]{'a', 'b', 'c'};
    }
    @Test
    public void powtorzoneArgumenty(){
        char[] argumenty = {'a', 'b', 'c', 'a'};
        assertThrows(DoubleDeclaration.class, () -> new Procedure(block, argumenty));
    }

    @Test
    public void zmienne(){
        Procedure proc = new Procedure(block, arg);
        assertEquals("[a, b, c]", proc.getArgs());
    }

    @Test
    public void deklaracje(){
        Procedure proc = new Procedure(block, arg);
        proc.addVariable('a', new Literal(123));
        proc.addVariable('b', new Variable('a'));
        assertThrows(DoubleDeclaration.class, () -> proc.addVariable('a', new Literal(456)));
    }

    @Test
    public void ustawArgumenty(){
        Procedure proc = new Procedure(block, arg);
        proc.setArguments( List.of(new Literal(123), new Literal(456), new Literal(789)));
        assertEquals("[a, b, c]\n" +
                "DEKLARACJA: a = 123\n" +
                "DEKLARACJA: b = 456\n" +
                "DEKLARACJA: c = 789\n", proc.toString());
    }

    @Test
    public void bezparametrow(){
        Procedure proc = new Procedure(block);
        proc.addIntruction(new Print(new Literal(125)));
        block.addProcedure("proc", proc);
        assertDoesNotThrow( () ->  block.addIntruction(new ProcedureCall("proc", block)));
        assertEquals("[]\nPRINT( 125 )\n", proc.toString());
    }
    @Test
    public void argumentPlusDeklaracja(){
        Procedure proc = new Procedure(block, arg);
        proc.addVariable('a', new Literal(123));
        block.addProcedure("proc", proc);
        block.addIntruction(new ProcedureCall("proc",
                List.of(new Literal(123), new Literal(456), new Literal(789)),
                block));

        assertDoesNotThrow(() -> block.execute()); //mozna zadeklarowac w procedurze zmiennej o tej samej nazwei co
        // argument
    }

}
