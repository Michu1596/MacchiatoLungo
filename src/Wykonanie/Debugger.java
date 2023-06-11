package Wykonanie;

import Instrukcje.*;
import Wyrazenia.*;

public class Debugger {
    static private int licznik = 0;
    protected Instrukcja ktoraNastepna;

    public static void main(String[] args){

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
        //program.wykonaj();

        /*Debugger debugger = new Debugger();
        InstrukcjaPojedyncza nast = program.nastepnaInstrukcjaPojedyncza(debugger);
        while (nast != null){
            nast.wykonaj();
            if(debugger.ktoraNastepna != null) {
                String nastepna2 = debugger.ktoraNastepna.toString();
                System.out.println(nastepna2);
                System.out.println("----------------\n");
            }
            nast = program.nastepnaInstrukcjaPojedyncza(debugger);
        }*/
        Debugger debugger = new Debugger();
        int i = 0;
        InstrukcjaPojedyncza nast = program.nastepnaInstrukcjaPojedyncza(debugger);
        while (nast != null && i < 1000){
            nast.wykonaj();
            //System.out.println(nast.toString());
            String nastepna2 = debugger.ktoraNastepna.toString();
            nast = program.nastepnaInstrukcjaPojedyncza(debugger);
            //System.out.println("*************\n");
            i++;
        }
        System.out.println("----------------\n");
        String nastepna2 = debugger.ktoraNastepna.toString();
        System.out.println(nastepna2);
        System.out.println("----------------\n");
        System.out.println(nast.display(0).toString());

    }

    public void setKtoraNastepna(Instrukcja nastepna){
        ktoraNastepna = nastepna;
    }
    public Instrukcja getKtoraNastepna(){
        return ktoraNastepna;
    }
}
