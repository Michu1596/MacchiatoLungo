package Testy;

import Buildery.BlokBuilder;
import Instrukcje.*;
import Wyrazenia.Literal;
import Wyrazenia.Zmienna;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProceduraBuilderTesty {
    @Test
    public void deklaracja(){
        InstrukcjaZlozona blok = new BlokBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .rozpocznijProcedure("proc", new char[] {'a', 'b', 'c'})
                .zadeklarujZmienna('a', new Literal(561))
                .print(new Zmienna('a'))
                .zamknijZakres()
                .wywolanieProcedury("proc", List.of(new Literal(1),
                        new Literal(2),new Literal(1)))
                .getInstrukcja();

        Blok wzor = new Blok();
        wzor.dodajDeklaracje('x', new Literal(7));
        Procedura proc = new Procedura(wzor, new char[] {'a', 'b', 'c'});
        proc.dodajDeklaracje('a', new Literal(561));
        proc.dodajInstrukcje(new Print(new Zmienna('a')));
        wzor.dodajProcedure("proc",proc);
        wzor.dodajInstrukcje(new WywolanieProcedury("proc", List.of(new Literal(1),
                new Literal(2),new Literal(1)), wzor));
        assertEquals(wzor.toString(), blok.toString());
    }

    @Test
    public void proceduraBezArgumentow(){
        InstrukcjaZlozona blok = new BlokBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .rozpocznijProcedure("proc")
                .zadeklarujZmienna('a', new Literal(561))
                .print(new Zmienna('x'))
                .zamknijZakres()
                .wywolanieProcedury("proc")
                .getInstrukcja();

        Blok wzor = new Blok();
        wzor.dodajDeklaracje('x', new Literal(7));
        Procedura proc = new Procedura(wzor);
        proc.dodajDeklaracje('a', new Literal(561));
        proc.dodajInstrukcje(new Print(new Zmienna('x')));
        wzor.dodajProcedure("proc",proc);
        wzor.dodajInstrukcje(new WywolanieProcedury("proc", wzor));
        assertEquals(wzor.toString(), blok.toString());
    }

    @Test
    public void proceduraZArgumentami(){
        InstrukcjaZlozona procedura = new BlokBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .rozpocznijProcedure("proc", new char[] {'a', 'b', 'c'})
                .zadeklarujZmienna('a', new Literal(561))
                .print(new Zmienna('a'))
                .getInstrukcja();

        Blok wzor = new Blok();
        wzor.dodajDeklaracje('x', new Literal(7));
        Procedura proc = new Procedura(wzor, new char[] {'a', 'b', 'c'});
        proc.dodajDeklaracje('a', new Literal(561));
        proc.dodajInstrukcje(new Print(new Zmienna('a')));
        wzor.dodajProcedure("proc",proc);
        wzor.dodajInstrukcje(new WywolanieProcedury("proc", List.of(new Literal(1),
                new Literal(2),new Literal(1)), wzor));
        assertEquals(proc.toString(), procedura.toString());
    }
}
