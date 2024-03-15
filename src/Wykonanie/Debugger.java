package Wykonanie;

import Instrukcje.*;
import Wyrazenia.*;

public class Debugger {
    static private int licznik = 0;
    protected Instruction ktoraNastepna;

    public static void main(String[] args){

        Block program = new Block();
        program.addDeclaration('n', new Literal(30));

        ForLoop petla = new ForLoop(program,
                'k',
                new Subtraction(new Variable('n'), new Literal(1)));
        Block wnetrzePetli = new Block(petla);
        wnetrzePetli.addDeclaration('p', new Literal(1));
        wnetrzePetli.addIntruction(new Przypisanie('k',
                new Addition(new Variable('k'),
                        new Literal(2))));

        ForLoop petal2 = new ForLoop(wnetrzePetli,
                'i',
                new Subtraction(new Variable('k'),
                        new Literal(2)));
        petal2.addIntruction(new Przypisanie('i', new Addition(new Variable('i'),
                new Literal(2))));
        Conditional warunkowa = new CondEqual(new Modulo(new Variable('k'),
                new Variable('i')), new Literal(0));
        petal2.addIntruction(warunkowa);
        warunkowa.addIntruction(new Przypisanie('p', new Literal(0)));

        Conditional warunkowa2 = new CondEqual(new Variable('p'), new Literal(1));
        wnetrzePetli.addIntruction(petal2);
        wnetrzePetli.addIntruction(warunkowa2);
        warunkowa2.addIntruction(new Print(new Variable('k')));

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
        SingleInstruction nast = program.nextSingleInstruction(debugger);
        while (nast != null && i < 1000){
            nast.execute();
            //System.out.println(nast.toString());
            String nastepna2 = debugger.ktoraNastepna.toString();
            nast = program.nextSingleInstruction(debugger);
            //System.out.println("*************\n");
            i++;
        }
        System.out.println("----------------\n");
        String nastepna2 = debugger.ktoraNastepna.toString();
        System.out.println(nastepna2);
        System.out.println("----------------\n");
        System.out.println(nast.display(0).toString());

    }

    public void setNextInstruction(Instruction nastepna){
        ktoraNastepna = nastepna;
    }
    public Instruction getNextInstruction(){
        return ktoraNastepna;
    }
}
