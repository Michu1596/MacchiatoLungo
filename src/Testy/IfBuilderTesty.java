package Testy;
import Buildery.BlokBuilder;
import Instrukcje.*;
import Wyrazenia.Literal;
import Wyrazenia.Zmienna;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class IfBuilderTesty {
    @Test
    public void IfRowne(){
        InstrukcjaZlozona blok = new BlokBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .przypisanie('x', new Literal(561))
                .rozpocznijInstrukcjeWarunkowa("==", new Zmienna('x'), new Literal(561))
                .print(new Zmienna('x'))
                .zamknijZakres()
                .getInstrukcja();

        Blok wzor = new Blok();
        wzor.dodajDeklaracje('x', new Literal(7));
        wzor.dodajInstrukcje(new Przypisanie('x', new Literal(561)));
        InstrukcjaWarunkowa warunkowa = new IfRowne(new Zmienna('x'), new Literal(561));
        warunkowa.dodajInstrukcje(new Print(new Zmienna('x')));
        wzor.dodajInstrukcje(warunkowa);

        assertEquals(wzor.toString(), blok.toString());
    }

    @Test
    public void IfMniejsze(){
        InstrukcjaZlozona blok = new BlokBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .przypisanie('x', new Literal(561))
                .rozpocznijInstrukcjeWarunkowa("<", new Zmienna('x'), new Literal(561))
                .print(new Zmienna('x'))
                .zamknijZakres()
                .getInstrukcja();

        Blok wzor = new Blok();
        wzor.dodajDeklaracje('x', new Literal(7));
        wzor.dodajInstrukcje(new Przypisanie('x', new Literal(561)));
        InstrukcjaWarunkowa warunkowa = new IfMniejsze(new Zmienna('x'), new Literal(561));
        warunkowa.dodajInstrukcje(new Print(new Zmienna('x')));
        wzor.dodajInstrukcje(warunkowa);

        assertEquals(wzor.toString(), blok.toString());
    }

    @Test
    public void IfMniejszeRowne(){
        InstrukcjaZlozona blok = new BlokBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .przypisanie('x', new Literal(561))
                .rozpocznijInstrukcjeWarunkowa("<=", new Zmienna('x'), new Literal(561))
                .print(new Zmienna('x'))
                .zamknijZakres()
                .przypisanie('y', new Literal(25))
                .getInstrukcja();

        Blok wzor = new Blok();
        wzor.dodajDeklaracje('x', new Literal(7));
        wzor.dodajInstrukcje(new Przypisanie('x', new Literal(561)));
        InstrukcjaWarunkowa warunkowa = new IfMniejszeRowne(new Zmienna('x'), new Literal(561));
        warunkowa.dodajInstrukcje(new Print(new Zmienna('x')));
        wzor.dodajInstrukcje(warunkowa);
        wzor.dodajInstrukcje(new Przypisanie('y', new Literal(25)));

        assertEquals(wzor.toString(), blok.toString());
    }

    @Test
    public void IfWiekszeRowne(){
        InstrukcjaZlozona blok = new BlokBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .przypisanie('x', new Literal(561))
                .rozpocznijInstrukcjeWarunkowa(">=", new Zmienna('x'), new Literal(561))
                .print(new Zmienna('x'))
                .zamknijZakres()
                .przypisanie('y', new Literal(25))
                .getInstrukcja();

        Blok wzor = new Blok();
        wzor.dodajDeklaracje('x', new Literal(7));
        wzor.dodajInstrukcje(new Przypisanie('x', new Literal(561)));
        InstrukcjaWarunkowa warunkowa = new IfWiekszeRowne(new Zmienna('x'), new Literal(561));
        warunkowa.dodajInstrukcje(new Print(new Zmienna('x')));
        wzor.dodajInstrukcje(warunkowa);
        wzor.dodajInstrukcje(new Przypisanie('y', new Literal(25)));

        assertEquals(wzor.toString(), blok.toString());
    }

    @Test
    public void IfWieksze(){
        InstrukcjaZlozona blok = new BlokBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .przypisanie('x', new Literal(561))
                .rozpocznijInstrukcjeWarunkowa(">", new Zmienna('x'), new Literal(561))
                .print(new Zmienna('x'))
                .zamknijZakres()
                .przypisanie('y', new Literal(25))
                .getInstrukcja();

        Blok wzor = new Blok();
        wzor.dodajDeklaracje('x', new Literal(7));
        wzor.dodajInstrukcje(new Przypisanie('x', new Literal(561)));
        InstrukcjaWarunkowa warunkowa = new IfWieksze(new Zmienna('x'), new Literal(561));
        warunkowa.dodajInstrukcje(new Print(new Zmienna('x')));
        wzor.dodajInstrukcje(warunkowa);
        wzor.dodajInstrukcje(new Przypisanie('y', new Literal(25)));

        assertEquals(wzor.toString(), blok.toString());
    }


}
