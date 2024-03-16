package Tests;

import Instructions.Block;
import Instructions.Procedure;
import Wyjatki.UndeclaredProcedure;
import org.junit.jupiter.api.BeforeEach;

import SupportClasses.ProcedureVisibilityScope;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProcedureVisibilityScopeTests {
    protected Block block;
    @BeforeEach
    public void init(){
        block = new Block();
    }
    @Test
    public void test1(){
        ProcedureVisibilityScope level0 = new ProcedureVisibilityScope();
        ProcedureVisibilityScope level1 = new ProcedureVisibilityScope(level0);
        ProcedureVisibilityScope level2 = new ProcedureVisibilityScope(level1);
        Procedure pr = new Procedure(block);
        level0.declareProcedure("proc0", pr);
        assertEquals(pr, level2.get("proc0"));

    }

    @Test
    public void undeclared(){
        ProcedureVisibilityScope level0 = new ProcedureVisibilityScope();
        ProcedureVisibilityScope level1 = new ProcedureVisibilityScope(level0);
        ProcedureVisibilityScope level2 = new ProcedureVisibilityScope(level1);

        assertThrows(UndeclaredProcedure.class, () -> level2.get("undeclared"));
    }

    @Test
    public void toStringTest(){
        ProcedureVisibilityScope level0 = new ProcedureVisibilityScope();
        level0.declareProcedure("proc0", new Procedure(block));
        level0.declareProcedure("proc1", new Procedure(block, new char[]{'a', 'b'}));
        level0.declareProcedure("proc2", new Procedure(block));

        assertEquals("proc2( [] )\n" +
                "proc1( [a, b] )\n" +
                "proc0( [] )\n", level0.toString());
    }
}
