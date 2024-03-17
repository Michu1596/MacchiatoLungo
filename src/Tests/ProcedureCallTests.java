package Tests;

import Instructions.Block;
import Instructions.Procedure;
import Instructions.ProcedureCall;
import Exceptions.MacchiatosError;
import Exceptions.InvalidProcedureArgument;
import Exceptions.UndeclaredProcedure;
import Exceptions.UndeclaredVariable;
import Wyrazenia.Literal;
import Wyrazenia.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static  org.junit.jupiter.api.Assertions.*;

public class ProcedureCallTests {
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
    public void wrongArgumentNumber(){
        assertThrows(MacchiatosError.class, () -> new ProcedureCall("proc",
                                                                        List.of(new Literal(25)), block));
    }
    @Test
    public void procedureWithoutArgs(){
        Procedure bezArg = new Procedure(block);
        block.addProcedure("procedureWithoutArgs", bezArg);
        assertThrows(InvalidProcedureArgument.class, () -> new ProcedureCall("procedureWithoutArgs",
                                                    List.of(new Literal(25)), block));
    }
    @Test
    public void nonexistentProcedure(){
        assertThrows(UndeclaredProcedure.class, () -> new ProcedureCall("nonexistentProcedure", block));
    }
    @Test
    public void innerCall(){
        Block inner = new Block(block);
        inner.addDeclaration('x', new Literal(57));
        inner.addDeclaration('y', new Literal(561));
        inner.connectOuterBlock(block);
        ProcedureCall innerCall = new ProcedureCall("proc",
                    List.of(new Variable('x'), new Variable('y')), inner);
        inner.addIntruction(innerCall);
        assertDoesNotThrow(() -> block.execute());

        ProcedureCall zewnWyw = new ProcedureCall("proc",
                List.of(new Variable('x'), new Variable('y')), block);
        inner.addIntruction(innerCall);

        assertThrows(UndeclaredVariable.class, () -> zewnWyw.execute());
    }
}
