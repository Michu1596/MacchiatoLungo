import Buildery.ProgramBuilder;
import Fabryki.*;
import Wykonanie.Program;
import java.util.List;

public class Main {
    public static void main(String[] args){
        //Nowy przyklad - demonostruje statyczne wiazanie zmiennych
        Program program = new ProgramBuilder()
                .declareVariable('x', StalaFabryka.wartosc(101))
                .declareVariable('y', StalaFabryka.wartosc(1))
                .openProcedure("out", new char[] {'a'})
                    .print(DodawanieFabryka.dodawanie(ZmiennaFabryka.nazwa('x'), ZmiennaFabryka.nazwa('a')))
                .closeScope()
                .assignment('x',OdejmowanieFabryka.odejmowanie(ZmiennaFabryka.nazwa('x'),
                        ZmiennaFabryka.nazwa('y')))
                .procedureCall("out", List.of(ZmiennaFabryka.nazwa('x')))
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
                .print(ZmiennaFabryka.nazwa('a'))
                .closeScope()
                .assignment('x', OdejmowanieFabryka.odejmowanie(ZmiennaFabryka.nazwa('x'),
                        ZmiennaFabryka.nazwa('y')))
                .procedureCall("out", List.of(ZmiennaFabryka.nazwa('x')))
                .procedureCall("out", List.of(StalaFabryka.wartosc(125)))
                .build();


        program.wykonajZDebugowaniem();
        //program.wykonanieBezDebugowania();
    }
    public static void przykladzPoprzedniegoZadania(String[] args){
        Program program = new ProgramBuilder()
                .declareVariable('n', StalaFabryka.wartosc(30))
                .openLoopInstruction('k', OdejmowanieFabryka.odejmowanie(ZmiennaFabryka.nazwa('n'),
                                                                                    StalaFabryka.wartosc(1)))
                .openBlock()
                .declareVariable('p', StalaFabryka.wartosc(1))
                .assignment('k', DodawanieFabryka.dodawanie(ZmiennaFabryka.nazwa('k'),
                        StalaFabryka.wartosc(2)))
                .openLoopInstruction('i',OdejmowanieFabryka.odejmowanie(ZmiennaFabryka.nazwa('k'),
                        StalaFabryka.wartosc(2)))
                .assignment('i', DodawanieFabryka.dodawanie(ZmiennaFabryka.nazwa('i'),
                        StalaFabryka.wartosc(2)))
                .openIfInstruction("==", ModuloFabryka.modulo(ZmiennaFabryka.nazwa('k'),
                        ZmiennaFabryka.nazwa('i')), StalaFabryka.wartosc(0))
                .assignment('p', StalaFabryka.wartosc(0))
                .closeScope()
                .closeScope()
                .openIfInstruction("==", ZmiennaFabryka.nazwa('p'), StalaFabryka.wartosc(1))
                .print(ZmiennaFabryka.nazwa('k'))
                .closeScope()
                .closeScope()
                .closeScope()
                .build();
        program.wykonanieBezDebugowania();
        //program.wykonajZDebugowaniem();
    }

}