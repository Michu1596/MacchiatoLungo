package Tests;

import Builders.BlockBuilder;
import Builders.ProgramBuilder;
import Wyjatki.MacchiatosError;
import Wyrazenia.Literal;
import Wyrazenia.Variable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class BuilderTests {
    @Test
    public void invalidDeclaration(){
    assertThrows(MacchiatosError.class, ()-> new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Variable('x'))
                .openLoopInstruction('k', new Literal(3))
                .declareVariable('m', new Literal(5))
                .closeScope()
                .getInstruction());
    }
    @Test
    public void invalidOpeningArgumentProcedure(){
        assertThrows(MacchiatosError.class, ()-> new ProgramBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Variable('x'))
                .openLoopInstruction('k', new Literal(6))
                .openProcedure("proc", new char[]{'a', 'b'})
                .closeScope()
                .closeScope()
                .getInstruction());
        assertThrows(MacchiatosError.class, ()-> new ProgramBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Variable('x'))
                .openProcedure("proc", new char[]{'a', 'b'})
                .openProcedure("proc2", new char[]{'c', 'd'})
                .closeScope()
                .closeScope()
                .getInstruction());
    }

    @Test
    public void invalidOpeningProcedureWithoutArg(){
        assertThrows(MacchiatosError.class, ()-> new ProgramBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Variable('x'))
                .openLoopInstruction('k', new Literal(6))
                .openProcedure("proc")
                .closeScope()
                .closeScope()
                .getInstruction());
        assertThrows(MacchiatosError.class, ()-> new ProgramBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Variable('x'))
                .openProcedure("proc")
                .openProcedure("proc2")
                .closeScope()
                .closeScope()
                .getInstruction());
    }

    @Test
    public void invalidBuild(){
        assertThrows(MacchiatosError.class, ()-> new ProgramBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Variable('x'))
                .openProcedure("proc")
                .print(new Variable('x'))
                .build());
    }
}
