package Testy;

import Instrukcje.Block;
import Instrukcje.Procedure;
import Instrukcje.ProcedureCall;
import Wyjatki.MacchiatosError;
import Wyjatki.InvalidProcedureArgument;
import Wyjatki.NiezadeklarowanaProcedura;
import Wyjatki.UndeclaredVariable;
import Wyrazenia.Literal;
import Wyrazenia.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static  org.junit.jupiter.api.Assertions.*;

public class ProcedureCallTesty {
    protected Block block;
    protected Procedure proc;
    protected char[] arg;
    @BeforeEach
    public void init(){
        block = new Block();
        arg = new char[] {'a', 'b'};
        proc = new Procedure(block, arg);
        block.addProcedure("proc", proc);
    }
    @Test
    public void zlaLiczbaArgumentow(){
        assertThrows(MacchiatosError.class, () -> new ProcedureCall("proc",
                                                                        List.of(new Literal(25)), block));
    }
    @Test
    public void procBezArg(){
        Procedure bezArg = new Procedure(block);
        block.addProcedure("procBezArg", bezArg);
        assertThrows(InvalidProcedureArgument.class, () -> new ProcedureCall("procBezArg",
                                                    List.of(new Literal(25)), block));
    }
    @Test
    public void nieistniejacaProcedura(){
        assertThrows(NiezadeklarowanaProcedura.class, () -> new ProcedureCall("nieistniejcProcedura", block));
    }
    @Test
    public void wewnetrzneWywolanie(){
        Block wewn = new Block(block);
        wewn.addDeclaration('x', new Literal(57));
        wewn.addDeclaration('y', new Literal(561));
        wewn.connectOuterBlock(block);
        ProcedureCall wewnWyw = new ProcedureCall("proc",
                    List.of(new Variable('x'), new Variable('y')), wewn);
        wewn.addIntruction(wewnWyw);
        assertDoesNotThrow(() -> block.execute());

        ProcedureCall zewnWyw = new ProcedureCall("proc",
                List.of(new Variable('x'), new Variable('y')), block);
        wewn.addIntruction(wewnWyw);

        assertThrows(UndeclaredVariable.class, () -> zewnWyw.execute());
    }
}
