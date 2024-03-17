import Builders.ProgramBuilder;
import Factories.*;
import Execution.Program;
import java.util.List;

public class Main {
    public static void main(String[] args){
        //Nowy przyklad - demonostruje statyczne wiazanie zmiennych
        Program program = new ProgramBuilder()
                .declareVariable('x', ConstFactory.wartosc(101))
                .declareVariable('y', ConstFactory.wartosc(1))
                .openProcedure("out", new char[] {'a'})
                    .print(AdditionFactory.addition(VariableFactory.nazwa('x'), VariableFactory.nazwa('a')))
                .closeScope()
                .assignment('x', SubtractionFactory.subtraction(VariableFactory.nazwa('x'),
                        VariableFactory.nazwa('y')))
                .procedureCall("out", List.of(VariableFactory.nazwa('x')))
                .procedureCall("out", List.of(ConstFactory.wartosc(100)))
                .openBlock()
                .declareVariable('x', ConstFactory.wartosc(10))
                .procedureCall("out", List.of(ConstFactory.wartosc(100)))
                .closeScope()
                .build();


        program.executionWithDebug();
        //program.wykonanieBezDebugowania();
    }
    public static void staryPrzyklad(String[] args){
        Program program = new ProgramBuilder()
                .declareVariable('x', ConstFactory.wartosc(57))
                .declareVariable('y', ConstFactory.wartosc(15))
                .openProcedure("out", new char[] {'a'})
                .print(VariableFactory.nazwa('a'))
                .closeScope()
                .assignment('x', SubtractionFactory.subtraction(VariableFactory.nazwa('x'),
                        VariableFactory.nazwa('y')))
                .procedureCall("out", List.of(VariableFactory.nazwa('x')))
                .procedureCall("out", List.of(ConstFactory.wartosc(125)))
                .build();


        program.executionWithDebug();
        //program.wykonanieBezDebugowania();
    }
    public static void przykladzPoprzedniegoZadania(String[] args){
        Program program = new ProgramBuilder()
                .declareVariable('n', ConstFactory.wartosc(30))
                .openLoopInstruction('k', SubtractionFactory.subtraction(VariableFactory.nazwa('n'),
                                                                                    ConstFactory.wartosc(1)))
                .openBlock()
                .declareVariable('p', ConstFactory.wartosc(1))
                .assignment('k', AdditionFactory.addition(VariableFactory.nazwa('k'),
                        ConstFactory.wartosc(2)))
                .openLoopInstruction('i', SubtractionFactory.subtraction(VariableFactory.nazwa('k'),
                        ConstFactory.wartosc(2)))
                .assignment('i', AdditionFactory.addition(VariableFactory.nazwa('i'),
                        ConstFactory.wartosc(2)))
                .openIfInstruction("==", ModuloFactory.modulo(VariableFactory.nazwa('k'),
                        VariableFactory.nazwa('i')), ConstFactory.wartosc(0))
                .assignment('p', ConstFactory.wartosc(0))
                .closeScope()
                .closeScope()
                .openIfInstruction("==", VariableFactory.nazwa('p'), ConstFactory.wartosc(1))
                .print(VariableFactory.nazwa('k'))
                .closeScope()
                .closeScope()
                .closeScope()
                .build();
        program.executionWithoutDebug();
        //program.wykonajZDebugowaniem();
    }

}