package Testy;

import Instrukcje.Blok;
import Instrukcje.Print;
import Instrukcje.Procedura;
import Instrukcje.WywolanieProcedury;
import Wyjatki.PodwojnaDeklaracja;
import Wyrazenia.Dodawanie;
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
    protected Blok blok;
    char[] arg;
    @BeforeEach
    public void blok(){
        blok = new Blok();
        arg = new char[]{'a', 'b', 'c'};
    }
    @Test
    public void powtorzoneArgumenty(){
        char[] argumenty = {'a', 'b', 'c', 'a'};
        assertThrows(PodwojnaDeklaracja.class, () -> new Procedura(blok, argumenty));
    }

    @Test
    public void zmienne(){
        Procedura proc = new Procedura(blok, arg);
        assertEquals("[a, b, c]", proc.getArgumenty());
    }

    @Test
    public void deklaracje(){
        Procedura proc = new Procedura(blok, arg);
        proc.dodajDeklaracje('a', new Literal(123));
        proc.dodajDeklaracje('b', new Zmienna('a'));
        assertThrows(PodwojnaDeklaracja.class, () -> proc.dodajDeklaracje('a', new Literal(456)));
    }

    @Test
    public void ustawArgumenty(){
        Procedura proc = new Procedura(blok, arg);
        proc.ustawArgumenty( List.of(new Literal(123), new Literal(456), new Literal(789)));
        assertEquals("[a, b, c]\n" +
                "DEKLARACJA: a = 123\n" +
                "DEKLARACJA: b = 456\n" +
                "DEKLARACJA: c = 789\n", proc.toString());
    }

    @Test
    public void bezparametrow(){
        Procedura proc = new Procedura(blok);
        proc.dodajInstrukcje(new Print(new Literal(125)));
        blok.dodajProcedure("proc", proc);
        assertDoesNotThrow( () ->  blok.dodajInstrukcje(new WywolanieProcedury("proc", blok)));
        assertEquals("[]\nPRINT( 125 )\n", proc.toString());
    }
    @Test
    public void argumentPlusDeklaracja(){
        Procedura proc = new Procedura(blok, arg);
        proc.dodajDeklaracje('a', new Literal(123));
        blok.dodajProcedure("proc", proc);
        blok.dodajInstrukcje(new WywolanieProcedury("proc",
                List.of(new Literal(123), new Literal(456), new Literal(789)),
                blok));

        assertDoesNotThrow(() -> blok.wykonaj()); //mozna zadeklarowac w procedurze zmiennej o tej samej nazwei co
        // argument
    }

}
