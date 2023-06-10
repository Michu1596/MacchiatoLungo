package Testy;


import Buildery.BlokBuilder;
import Instrukcje.*;
import Wyrazenia.Literal;
import Wyrazenia.Zmienna;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BlokBuilderTesty {
    @Test
    public void deklaracja(){
        InstrukcjaZlozona blok = new BlokBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .zadeklarujZmienna('y', new Zmienna('x'))
                .getInstrukcja();
        Blok wzor = new Blok();
        wzor.dodajDeklaracje('x', new Literal(7));
        wzor.dodajDeklaracje('y', new Zmienna('x'));
        assertEquals(wzor.toString(), blok.toString());
    }
    @Test
    public void blokWewn(){
        InstrukcjaZlozona blok = new BlokBuilder()
        .zadeklarujZmienna('x', new Literal(7))
            .rozpocznijBlok()
            .zadeklarujZmienna('y', new Literal(561))
        .zamknijZakres()
        .getInstrukcja();

        Blok wzor = new Blok();
        wzor.dodajDeklaracje('x', new Literal(7));
        Blok wnetrze = new Blok(wzor);
        wnetrze.dodajDeklaracje('y', new Literal(561));
        wzor.dodajInstrukcje(wnetrze);

        assertEquals(wzor.toString(), blok.toString());
    }
    @Test
    public void instrukcjePojedyncze(){
        InstrukcjaZlozona blok = new BlokBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .przypisanie('x', new Literal(561))
                .print(new Zmienna('x'))
                .getInstrukcja();

        Blok wzor = new Blok();
        wzor.dodajDeklaracje('x', new Literal(7));
        wzor.dodajInstrukcje(new Przypisanie('x', new Literal(561)));
        wzor.dodajInstrukcje(new Print(new Zmienna('x')));

        assertEquals(wzor.toString(), blok.toString());
    }

    @Test
    public void procedura(){
        InstrukcjaZlozona blok = new BlokBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .rozpocznijProcedure("proc", new char[] {'a', 'b', 'c'})
                .print(new Zmienna('a'))
                .zamknijZakres()
                .wywolanieProcedury("proc",List.of(new Literal(1),
                        new Literal(2),new Literal(1)))
                .getInstrukcja();

        Blok wzor = new Blok();
        wzor.dodajDeklaracje('x', new Literal(7));
        Procedura proc = new Procedura(wzor, new char[] {'a', 'b', 'c'});
        proc.dodajInstrukcje(new Print(new Zmienna('a')));
        wzor.dodajProcedure("proc",proc);
        wzor.dodajInstrukcje(new WywolanieProcedury("proc", List.of(new Literal(1),
                new Literal(2),new Literal(1)), wzor));
        assertEquals(wzor.toString(), blok.toString());
    }
}
