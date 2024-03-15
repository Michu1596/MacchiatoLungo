package Testy;

import Instrukcje.Block;
import Instrukcje.Procedure;
import Wyjatki.NiezadeklarowanaProcedura;
import org.junit.jupiter.api.BeforeEach;

import KlasyPomocnicze.ProcedureVisibilityScope;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProcedureVisibilityScopeTesty {
    protected Block block;
    @BeforeEach
    public void init(){
        block = new Block();
    }
    @Test
    public void test1(){
        ProcedureVisibilityScope poziom0 = new ProcedureVisibilityScope();
        ProcedureVisibilityScope poziom1 = new ProcedureVisibilityScope(poziom0);
        ProcedureVisibilityScope poziom2 = new ProcedureVisibilityScope(poziom1);
        Procedure pr = new Procedure(block);
        poziom0.declareProcedure("proc0", pr);
        assertEquals(pr, poziom2.get("proc0"));

    }

    @Test
    public void niezadeklarowana(){
        ProcedureVisibilityScope poziom0 = new ProcedureVisibilityScope();
        ProcedureVisibilityScope poziom1 = new ProcedureVisibilityScope(poziom0);
        ProcedureVisibilityScope poziom2 = new ProcedureVisibilityScope(poziom1);

        assertThrows(NiezadeklarowanaProcedura.class, () -> poziom2.get("niezadeklarowana"));
    }

    @Test
    public void toStringTest(){
        ProcedureVisibilityScope poziom0 = new ProcedureVisibilityScope();
        poziom0.declareProcedure("proc0", new Procedure(block));
        poziom0.declareProcedure("proc1", new Procedure(block, new char[]{'a', 'b'}));
        poziom0.declareProcedure("proc2", new Procedure(block));

        assertEquals("proc2( [] )\n" +
                "proc1( [a, b] )\n" +
                "proc0( [] )\n", poziom0.toString());
    }
}
