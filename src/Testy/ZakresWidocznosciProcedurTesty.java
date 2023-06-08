package Testy;

import Instrukcje.Blok;
import Instrukcje.Procedura;
import Instrukcje.WywolanieProcedury;
import Wyjatki.BladMacchiato;
import Wyjatki.NieprawidloweArgumentyProcedury;
import Wyjatki.NiezadeklarowanaProcedura;
import Wyjatki.NiezadeklarowanaZmienna;
import Wyrazenia.Literal;
import Wyrazenia.Zmienna;
import org.junit.jupiter.api.BeforeEach;

import Instrukcje.Procedura;
import KlasyPomocnicze.ZakresWidocznosciProcedur;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZakresWidocznosciProcedurTesty {
    protected Blok blok;
    @BeforeEach
    public void init(){
        blok = new Blok();
    }
    @Test
    public void test1(){
        ZakresWidocznosciProcedur poziom0 = new ZakresWidocznosciProcedur();
        ZakresWidocznosciProcedur poziom1 = new ZakresWidocznosciProcedur(poziom0);
        ZakresWidocznosciProcedur poziom2 = new ZakresWidocznosciProcedur(poziom1);
        Procedura pr = new Procedura(blok);
        poziom0.deklarujProcedure("proc0", pr);
        assertEquals(pr, poziom2.get("proc0"));

    }

    @Test
    public void niezadeklarowana(){
        ZakresWidocznosciProcedur poziom0 = new ZakresWidocznosciProcedur();
        ZakresWidocznosciProcedur poziom1 = new ZakresWidocznosciProcedur(poziom0);
        ZakresWidocznosciProcedur poziom2 = new ZakresWidocznosciProcedur(poziom1);

        assertThrows(NiezadeklarowanaProcedura.class, () -> poziom2.get("niezadeklarowana"));
    }

    @Test
    public void toStringTest(){
        ZakresWidocznosciProcedur poziom0 = new ZakresWidocznosciProcedur();
        poziom0.deklarujProcedure("proc0", new Procedura(blok));
        poziom0.deklarujProcedure("proc1", new Procedura(blok, new char[]{'a', 'b'}));
        poziom0.deklarujProcedure("proc2", new Procedura(blok));

        assertEquals("proc2( [] )\n" +
                "proc1( [a, b] )\n" +
                "proc0( [] )\n", poziom0.toString());
    }
}
