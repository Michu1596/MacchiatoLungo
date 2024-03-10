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
                .beginProcedure("out", new char[] {'a'})
                    .print(DodawanieFabryka.dodawanie(ZmiennaFabryka.nazwa('x'), ZmiennaFabryka.nazwa('a')))
                .finishScope()
                .przypisanie('x',OdejmowanieFabryka.odejmowanie(ZmiennaFabryka.nazwa('x'),
                        ZmiennaFabryka.nazwa('y')))
                .wywolanieProcedury("out", List.of(ZmiennaFabryka.nazwa('x')))
                .wywolanieProcedury("out", List.of(StalaFabryka.wartosc(100)))
                .beginBlock()
                .declareVariable('x', StalaFabryka.wartosc(10))
                .wywolanieProcedury("out", List.of(StalaFabryka.wartosc(100)))
                .finishScope()
                .zbuduj();


        program.wykonajZDebugowaniem();
        //program.wykonanieBezDebugowania();
    }
    public static void staryPrzyklad(String[] args){
        Program program = new ProgramBuilder()
                .declareVariable('x', StalaFabryka.wartosc(57))
                .declareVariable('y', StalaFabryka.wartosc(15))
                .beginProcedure("out", new char[] {'a'})
                .print(ZmiennaFabryka.nazwa('a'))
                .finishScope()
                .przypisanie('x', OdejmowanieFabryka.odejmowanie(ZmiennaFabryka.nazwa('x'),
                        ZmiennaFabryka.nazwa('y')))
                .wywolanieProcedury("out", List.of(ZmiennaFabryka.nazwa('x')))
                .wywolanieProcedury("out", List.of(StalaFabryka.wartosc(125)))
                .zbuduj();


        program.wykonajZDebugowaniem();
        //program.wykonanieBezDebugowania();
    }
    public static void przykladzPoprzedniegoZadania(String[] args){
        Program program = new ProgramBuilder()
                .declareVariable('n', StalaFabryka.wartosc(30))
                .rozpocznijPetle('k', OdejmowanieFabryka.odejmowanie(ZmiennaFabryka.nazwa('n'),
                                                                                    StalaFabryka.wartosc(1)))
                .beginBlock()
                .declareVariable('p', StalaFabryka.wartosc(1))
                .przypisanie('k', DodawanieFabryka.dodawanie(ZmiennaFabryka.nazwa('k'),
                        StalaFabryka.wartosc(2)))
                .rozpocznijPetle('i',OdejmowanieFabryka.odejmowanie(ZmiennaFabryka.nazwa('k'),
                        StalaFabryka.wartosc(2)))
                .przypisanie('i', DodawanieFabryka.dodawanie(ZmiennaFabryka.nazwa('i'),
                        StalaFabryka.wartosc(2)))
                .rozpocznijInstrukcjeWarunkowa("==", ModuloFabryka.modulo(ZmiennaFabryka.nazwa('k'),
                        ZmiennaFabryka.nazwa('i')), StalaFabryka.wartosc(0))
                .przypisanie('p', StalaFabryka.wartosc(0))
                .finishScope()
                .finishScope()
                .rozpocznijInstrukcjeWarunkowa("==", ZmiennaFabryka.nazwa('p'), StalaFabryka.wartosc(1))
                .print(ZmiennaFabryka.nazwa('k'))
                .finishScope()
                .finishScope()
                .finishScope()
                .zbuduj();
        program.wykonanieBezDebugowania();
        //program.wykonajZDebugowaniem();
    }

}