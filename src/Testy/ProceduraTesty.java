package Testy;

import Instrukcje.Block;
import Instrukcje.Print;
import Instrukcje.Procedura;
import Instrukcje.WywolanieProcedury;
import Wyjatki.DoubleDeclaration;
import Wyrazenia.Literal;
import Wyrazenia.Zmienna;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testy klasy Procedura
 */
public class ProceduraTesty {
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
        assertThrows(DoubleDeclaration.class, () -> new Procedura(block, argumenty));
    }

    @Test
    public void zmienne(){
        Procedura proc = new Procedura(block, arg);
        assertEquals("[a, b, c]", proc.getArgumenty());
    }

    @Test
    public void deklaracje(){
        Procedura proc = new Procedura(block, arg);
        proc.dodajDeklaracje('a', new Literal(123));
        proc.dodajDeklaracje('b', new Zmienna('a'));
        assertThrows(DoubleDeclaration.class, () -> proc.dodajDeklaracje('a', new Literal(456)));
    }

    @Test
    public void ustawArgumenty(){
        Procedura proc = new Procedura(block, arg);
        proc.ustawArgumenty( List.of(new Literal(123), new Literal(456), new Literal(789)));
        assertEquals("[a, b, c]\n" +
                "DEKLARACJA: a = 123\n" +
                "DEKLARACJA: b = 456\n" +
                "DEKLARACJA: c = 789\n", proc.toString());
    }

    @Test
    public void bezparametrow(){
        Procedura proc = new Procedura(block);
        proc.addIntruction(new Print(new Literal(125)));
        block.dodajProcedure("proc", proc);
        assertDoesNotThrow( () ->  block.addIntruction(new WywolanieProcedury("proc", block)));
        assertEquals("[]\nPRINT( 125 )\n", proc.toString());
    }
    @Test
    public void argumentPlusDeklaracja(){
        Procedura proc = new Procedura(block, arg);
        proc.dodajDeklaracje('a', new Literal(123));
        block.dodajProcedure("proc", proc);
        block.addIntruction(new WywolanieProcedury("proc",
                List.of(new Literal(123), new Literal(456), new Literal(789)),
                block));

        assertDoesNotThrow(() -> block.wykonaj()); //mozna zadeklarowac w procedurze zmiennej o tej samej nazwei co
        // argument
    }

}
