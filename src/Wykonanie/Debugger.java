package Wykonanie;

import Instrukcje.*;
import Wyrazenia.*;

public class Debugger {
    static private int licznik = 0;
    protected Instrukcja ktoraNastepna;

    public static void main(String[] args){

        Block program = new Block();
        program.addDeclaration('n', new Literal(30));

        PetlaFor petla = new PetlaFor(program,
                'k',
                new Odejmowanie(new Zmienna('n'), new Literal(1)));
        Block wnetrzePetli = new Block(petla);
        wnetrzePetli.addDeclaration('p', new Literal(1));
        wnetrzePetli.addIntruction(new Przypisanie('k',
                new Dodawanie(new Zmienna('k'),
                        new Literal(2))));

        PetlaFor petal2 = new PetlaFor(wnetrzePetli,
                'i',
                new Odejmowanie(new Zmienna('k'),
                        new Literal(2)));
        petal2.addIntruction(new Przypisanie('i', new Dodawanie(new Zmienna('i'),
                new Literal(2))));
        InstrukcjaWarunkowa warunkowa = new IfRowne(new Modulo(new Zmienna('k'),
                new Zmienna('i')), new Literal(0));
        petal2.addIntruction(warunkowa);
        warunkowa.addIntruction(new Przypisanie('p', new Literal(0)));

        InstrukcjaWarunkowa warunkowa2 = new IfRowne(new Zmienna('p'), new Literal(1));
        wnetrzePetli.addIntruction(petal2);
        wnetrzePetli.addIntruction(warunkowa2);
        warunkowa2.addIntruction(new Print(new Zmienna('k')));

        petla.addIntruction(wnetrzePetli);
        program.addIntruction(petla);
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
