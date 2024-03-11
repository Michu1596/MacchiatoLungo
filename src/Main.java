import Builders.ProgramBuilder;
import Factories.*;
import Wykonanie.Program;
import java.util.List;

public class Main {
    public static void main(String[] args){
        //Nowy przyklad - demonostruje statyczne wiazanie zmiennych
        Program program = new ProgramBuilder()
                .declareVariable('x', StalaFabryka.wartosc(101))
                .declareVariable('y', StalaFabryka.wartosc(1))
                .openProcedure("out", new char[] {'a'})
                    .print(AdditionFactory.addition(VariableFactory.nazwa('x'), VariableFactory.nazwa('a')))
                .closeScope()
                .assignment('x', SubtractionFactory.subtraction(VariableFactory.nazwa('x'),
                        VariableFactory.nazwa('y')))
                .procedureCall("out", List.of(VariableFactory.nazwa('x')))
                .procedureCall("out", List.of(StalaFabryka.wartosc(100)))
                .openBlock()
                .declareVariable('x', StalaFabryka.wartosc(10))
                .procedureCall("out", List.of(StalaFabryka.wartosc(100)))
                .closeScope()
                .build();


        program.wykonajZDebugowaniem();
        //program.wykonanieBezDebugowania();
    }
    public static void staryPrzyklad(String[] args){
        Program program = new ProgramBuilder()
                .declareVariable('x', StalaFabryka.wartosc(57))
                .declareVariable('y', StalaFabryka.wartosc(15))
                .openProcedure("out", new char[] {'a'})
                .print(VariableFactory.nazwa('a'))
                .closeScope()
                .assignment('x', SubtractionFactory.subtraction(VariableFactory.nazwa('x'),
                        VariableFactory.nazwa('y')))
                .procedureCall("out", List.of(VariableFactory.nazwa('x')))
                .procedureCall("out", List.of(StalaFabryka.wartosc(125)))
                .build();


        program.wykonajZDebugowaniem();
        //program.wykonanieBezDebugowania();
    }
    public static void przykladzPoprzedniegoZadania(String[] args){
        Program program = new ProgramBuilder()
                .declareVariable('n', StalaFabryka.wartosc(30))
                .openLoopInstruction('k', SubtractionFactory.subtraction(VariableFactory.nazwa('n'),
                                                                                    StalaFabryka.wartosc(1)))
                .openBlock()
                .declareVariable('p', StalaFabryka.wartosc(1))
                .assignment('k', AdditionFactory.addition(VariableFactory.nazwa('k'),
                        StalaFabryka.wartosc(2)))
                .openLoopInstruction('i', SubtractionFactory.subtraction(VariableFactory.nazwa('k'),
                        StalaFabryka.wartosc(2)))
                .assignment('i', AdditionFactory.addition(VariableFactory.nazwa('i'),
                        StalaFabryka.wartosc(2)))
                .openIfInstruction("==", ModuloFactory.modulo(VariableFactory.nazwa('k'),
                        VariableFactory.nazwa('i')), StalaFabryka.wartosc(0))
                .assignment('p', StalaFabryka.wartosc(0))
                .closeScope()
                .closeScope()
                .openIfInstruction("==", VariableFactory.nazwa('p'), StalaFabryka.wartosc(1))
                .print(VariableFactory.nazwa('k'))
                .closeScope()
                .closeScope()
                .closeScope()
                .build();
        program.wykonanieBezDebugowania();
        //program.wykonajZDebugowaniem();
    }

}