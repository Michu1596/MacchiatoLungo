import Instrukcje.*;
import Wykonanie.Program;
import Wyrazenia.*;
import Wyjatki.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

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