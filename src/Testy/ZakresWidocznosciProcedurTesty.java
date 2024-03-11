package Testy;

import Instrukcje.Block;
import Instrukcje.Procedure;
import Wyjatki.NiezadeklarowanaProcedura;
import org.junit.jupiter.api.BeforeEach;

import KlasyPomocnicze.ZakresWidocznosciProcedur;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZakresWidocznosciProcedurTesty {
    protected Block block;
    @BeforeEach
    public void init(){
        block = new Block();
    }
    @Test
    public void test1(){
        ZakresWidocznosciProcedur poziom0 = new ZakresWidocznosciProcedur();
        ZakresWidocznosciProcedur poziom1 = new ZakresWidocznosciProcedur(poziom0);
        ZakresWidocznosciProcedur poziom2 = new ZakresWidocznosciProcedur(poziom1);
        Procedure pr = new Procedure(block);
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
        poziom0.deklarujProcedure("proc0", new Procedure(block));
        poziom0.deklarujProcedure("proc1", new Procedure(block, new char[]{'a', 'b'}));
        poziom0.deklarujProcedure("proc2", new Procedure(block));

        assertEquals("proc2( [] )\n" +
                "proc1( [a, b] )\n" +
                "proc0( [] )\n", poziom0.toString());
    }
}
