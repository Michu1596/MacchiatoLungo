import Buildery.ProgramBuilder;
import Fabryki.*;
import Wykonanie.Program;
import java.util.List;

public class Main {
    public static void main(String[] args){
        //Nowy przyklad
        Program program = new ProgramBuilder()
                .zadeklarujZmienna('x', StalaFabryka.wartosc(101))
                .zadeklarujZmienna('y', StalaFabryka.wartosc(1))
                .rozpocznijProcedure("out", new char[] {'a'})
                    .print(DodawanieFabryka.dodawanie(ZmiennaFabryka.nazwa('x'), ZmiennaFabryka.nazwa('a')))
                .zamknijZakres()
                .przypisanie('x',OdejmowanieFabryka.odejmowanie(ZmiennaFabryka.nazwa('x'),
                        ZmiennaFabryka.nazwa('y')))
                .wywolanieProcedury("out", List.of(ZmiennaFabryka.nazwa('x')))
                .wywolanieProcedury("out", List.of(StalaFabryka.wartosc(100)))
                .rozpocznijBlok()
                .zadeklarujZmienna('x', StalaFabryka.wartosc(10))
                .wywolanieProcedury("out", List.of(StalaFabryka.wartosc(100)))
                .zamknijZakres()
                .zbuduj();


        program.wykonajZDebugowaniem();
        //program.wykonanieBezDebugowania();
    }
    public static void staryPrzyklad(String[] args){
        Program program = new ProgramBuilder()
                .zadeklarujZmienna('x', StalaFabryka.wartosc(57))
                .zadeklarujZmienna('y', StalaFabryka.wartosc(15))
                .rozpocznijProcedure("out", new char[] {'a'})
                .print(ZmiennaFabryka.nazwa('a'))
                .zamknijZakres()
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
                .zadeklarujZmienna('n', StalaFabryka.wartosc(30))
                .rozpocznijPetle('k', OdejmowanieFabryka.odejmowanie(ZmiennaFabryka.nazwa('n'),
                                                                                    StalaFabryka.wartosc(1)))
                .rozpocznijBlok()
                .zadeklarujZmienna('p', StalaFabryka.wartosc(1))
                .przypisanie('k', DodawanieFabryka.dodawanie(ZmiennaFabryka.nazwa('k'),
                        StalaFabryka.wartosc(2)))
                .rozpocznijPetle('i',OdejmowanieFabryka.odejmowanie(ZmiennaFabryka.nazwa('k'),
                        StalaFabryka.wartosc(2)))
                .przypisanie('i', DodawanieFabryka.dodawanie(ZmiennaFabryka.nazwa('i'),
                        StalaFabryka.wartosc(2)))
                .rozpocznijInstrukcjeWarunkowa("==", ModuloFabryka.modulo(ZmiennaFabryka.nazwa('k'),
                        ZmiennaFabryka.nazwa('i')), StalaFabryka.wartosc(0))
                .przypisanie('p', StalaFabryka.wartosc(0))
                .zamknijZakres()
                .zamknijZakres()
                .rozpocznijInstrukcjeWarunkowa("==", ZmiennaFabryka.nazwa('p'), StalaFabryka.wartosc(1))
                .print(ZmiennaFabryka.nazwa('k'))
                .zamknijZakres()
                .zamknijZakres()
                .zamknijZakres()
                .zbuduj();
        program.wykonanieBezDebugowania();
        //program.wykonajZDebugowaniem();
    }

}