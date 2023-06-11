import Buildery.BlokBuilder;
//import Buildery.ProgramBuilder;
import Buildery.ProgramBuilder;
import Fabryki.*;
import Instrukcje.*;
import Wykonanie.Program;
import Wyrazenia.*;
import Wyjatki.*;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void nmain(String[] args){
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
    }
    public static void main(String[] args){
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
    public static void nieMain3(String[] args){
       // ProgramBuilder bldr = new ProgramBuilder().rozpocznijBlok().zadeklarujZmienna('n', new Literal(1)).zakonczBlok();
        Program program = new ProgramBuilder()
                .zadeklarujZmienna('x', new Literal(57))
                .zadeklarujZmienna('y', new Literal(15))
                .rozpocznijProcedure("out", new char[] {'a'})
                    .print(new Zmienna('a'))
                .zamknijZakres()
                .przypisanie('x', new Odejmowanie(new Zmienna('x'), new Zmienna('y')))
                .wywolanieProcedury("out", List.of(new Zmienna('x')))
                .wywolanieProcedury("out", List.of(new Literal(125)))
                .zbuduj();


        //program.wykonajZDebugowaniem();
        program.wykonanieBezDebugowania();
    }
    public static void nieMain2(String[] args){
        Blok program = new Blok();
        program.dodajDeklaracje('x', new Literal(57));
        program.dodajDeklaracje('y', new Literal(15));
        char[] argumenty = new char[1];
        argumenty[0] = 'a';
        Procedura out = new Procedura(program, argumenty);
        out.dodajInstrukcje(new Print(new Zmienna('a')));
        program.dodajProcedure("out", out);
        program.dodajInstrukcje(new Przypisanie('x',
                                    new Odejmowanie(
                                            new Zmienna('x'),
                                            new Zmienna('y'))));
        List<Wyrazenie> param = new ArrayList<Wyrazenie>();
        param.add(new Zmienna('x'));
        program.dodajInstrukcje(new WywolanieProcedury("out", param, program));
        //param.clear();
        List<Wyrazenie> param2 = new ArrayList<Wyrazenie>();
        param2.add(new Literal(125));
        program.dodajInstrukcje(new WywolanieProcedury("out", param2, program));

        Program procedury = new Program(program);
        //procedury.wykonanieBezDebugowania();
        procedury.wykonajZDebugowaniem();
    }
    public static void nieMain(String[] args) {

        Blok program = new Blok();
        program.dodajDeklaracje('n', new Literal(30));

        PetlaFor petla = new PetlaFor(program,
                        'k',
                        new Odejmowanie(new Zmienna('n'), new Literal(1)));
        Blok wnetrzePetli = new Blok(petla);
        wnetrzePetli.dodajDeklaracje('p', new Literal(1));
        wnetrzePetli.dodajInstrukcje(new Przypisanie('k',
                                                     new Dodawanie(new Zmienna('k'),
                                                     new Literal(2))));

        PetlaFor petal2 = new PetlaFor(wnetrzePetli,
                            'i',
                                         new Odejmowanie(new Zmienna('k'),
                                                         new Literal(2)));
        petal2.dodajInstrukcje(new Przypisanie('i', new Dodawanie(new Zmienna('i'),
                                                                        new Literal(2))));
        InstrukcjaWarunkowa warunkowa = new IfRowne(new Modulo(new Zmienna('k'),
                                                    new Zmienna('i')), new Literal(0));
        petal2.dodajInstrukcje(warunkowa);
        warunkowa.dodajInstrukcje(new Przypisanie('p', new Literal(0)));

        InstrukcjaWarunkowa warunkowa2 = new IfRowne(new Zmienna('p'), new Literal(1));
        wnetrzePetli.dodajInstrukcje(petal2);
        wnetrzePetli.dodajInstrukcje(warunkowa2);
        warunkowa2.dodajInstrukcje(new Print(new Zmienna('k')));

        petla.dodajInstrukcje(wnetrzePetli);
        program.dodajInstrukcje(petla);


        Program przyklad = new Program(program);
        przyklad.wykonajZDebugowaniem();
    }
}