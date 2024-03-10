package Testy;

import Buildery.BlockBuilder;
import Buildery.ProgramBuilder;
import Wyjatki.BladMacchiato;
import Wyrazenia.Literal;
import Wyrazenia.Zmienna;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class BuilderTesty {
    @Test
    public void niepoprawnaDeklaracja(){
    assertThrows(BladMacchiato.class, ()-> new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Zmienna('x'))
                .rozpocznijPetle('k', new Literal(3))
                .declareVariable('m', new Literal(5))
                .finishScope()
                .getInstruction());
    }
    @Test
    public void niepoprawnaRozpoczecieProceduryZArg(){
        assertThrows(BladMacchiato.class, ()-> new ProgramBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Zmienna('x'))
                .rozpocznijPetle('k', new Literal(6))
                .beginProcedure("proc", new char[]{'a', 'b'})
                .finishScope()
                .finishScope()
                .getInstruction());
        assertThrows(BladMacchiato.class, ()-> new ProgramBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Zmienna('x'))
                .beginProcedure("proc", new char[]{'a', 'b'})
                .beginProcedure("proc2", new char[]{'c', 'd'})
                .finishScope()
                .finishScope()
                .getInstruction());
    }

    @Test
    public void niepoprawnaRozpoczecieProceduryBezArg(){
        assertThrows(BladMacchiato.class, ()-> new ProgramBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Zmienna('x'))
                .rozpocznijPetle('k', new Literal(6))
                .beginProcedure("proc")
                .finishScope()
                .finishScope()
                .getInstruction());
        assertThrows(BladMacchiato.class, ()-> new ProgramBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Zmienna('x'))
                .beginProcedure("proc")
                .beginProcedure("proc2")
                .finishScope()
                .finishScope()
                .getInstruction());
    }

    @Test
    public void niepoprawnaZbudowanie(){
        assertThrows(BladMacchiato.class, ()-> new ProgramBuilder()
                .declareVariable('x', new Literal(7))
                .declareVariable('y', new Zmienna('x'))
                .beginProcedure("proc")
                .print(new Zmienna('x'))
                .zbuduj());
    }
}
