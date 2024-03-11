package Testy;

import Builders.BlockBuilder;
import Builders.ProgramBuilder;
import Wyjatki.MacchiatosError;
import Wyrazenia.Literal;
import Wyrazenia.Variable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class BuilderTesty {
    @Test
    public void niepoprawnaDeklaracja(){
    assertThrows(MacchiatosError.class, ()-> new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Variable('x'))
                .openLoopInstruction('k', new Literal(3))
                .declareVariable('m', new Literal(5))
                .closeScope()
                .getInstruction());
    }
    @Test
    public void niepoprawnaRozpoczecieProceduryZArg(){
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
    public void niepoprawnaRozpoczecieProceduryBezArg(){
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
    public void niepoprawnaZbudowanie(){
        assertThrows(MacchiatosError.class, ()-> new ProgramBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Variable('x'))
                .openProcedure("proc")
                .print(new Variable('x'))
                .build());
    }
}
