package Testy;

import Buildery.BlokBuilder;
import Buildery.ProgramBuilder;
import Instrukcje.Blok;
import Instrukcje.InstrukcjaZlozona;
import Wyjatki.BladMacchiato;
import Wyrazenia.Literal;
import Wyrazenia.Zmienna;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class BuilderTesty {
    @Test
    public void niepoprawnaDeklaracja(){
    assertThrows(BladMacchiato.class, ()-> new BlokBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .zadeklarujZmienna('y', new Zmienna('x'))
                .rozpocznijPetle('k', new Literal(3))
                .zadeklarujZmienna('m', new Literal(5))
                .zamknijZakres()
                .getInstrukcja());
    }
    @Test
    public void niepoprawnaRozpoczecieProceduryZArg(){
        assertThrows(BladMacchiato.class, ()-> new ProgramBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .zadeklarujZmienna('y', new Zmienna('x'))
                .rozpocznijPetle('k', new Literal(6))
                .rozpocznijProcedure("proc", new char[]{'a', 'b'})
                .zamknijZakres()
                .zamknijZakres()
                .getInstrukcja());
        assertThrows(BladMacchiato.class, ()-> new ProgramBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .zadeklarujZmienna('y', new Zmienna('x'))
                .rozpocznijProcedure("proc", new char[]{'a', 'b'})
                .rozpocznijProcedure("proc2", new char[]{'c', 'd'})
                .zamknijZakres()
                .zamknijZakres()
                .getInstrukcja());
    }

    @Test
    public void niepoprawnaRozpoczecieProceduryBezArg(){
        assertThrows(BladMacchiato.class, ()-> new ProgramBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .zadeklarujZmienna('y', new Zmienna('x'))
                .rozpocznijPetle('k', new Literal(6))
                .rozpocznijProcedure("proc")
                .zamknijZakres()
                .zamknijZakres()
                .getInstrukcja());
        assertThrows(BladMacchiato.class, ()-> new ProgramBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .zadeklarujZmienna('y', new Zmienna('x'))
                .rozpocznijProcedure("proc")
                .rozpocznijProcedure("proc2")
                .zamknijZakres()
                .zamknijZakres()
                .getInstrukcja());
    }

    @Test
    public void niepoprawnaZbudowanie(){
        assertThrows(BladMacchiato.class, ()-> new ProgramBuilder()
                .zadeklarujZmienna('x', new Literal(7))
                .zadeklarujZmienna('y', new Zmienna('x'))
                .rozpocznijProcedure("proc")
                .print(new Zmienna('x'))
                .zbuduj());
    }
}
