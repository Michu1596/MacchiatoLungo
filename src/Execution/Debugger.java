package Execution;

import Instructions.*;
import Wyrazenia.*;

public class Debugger {
    protected Instruction nextInstruction;

    public static void main(String[] args){

        Block program = new Block();
        program.addDeclaration('n', new Literal(30));

        ForLoop loop = new ForLoop(program,
                'k',
                new Subtraction(new Variable('n'), new Literal(1)));
        Block loopInterior = new Block(loop);
        loopInterior.addDeclaration('p', new Literal(1));
        loopInterior.addIntruction(new Assignment('k',
                new Addition(new Variable('k'),
                        new Literal(2))));

        ForLoop loop2 = new ForLoop(loopInterior,
                'i',
                new Subtraction(new Variable('k'),
                        new Literal(2)));
        loop2.addIntruction(new Assignment('i', new Addition(new Variable('i'),
                new Literal(2))));
        Conditional conditional = new CondEqual(new Modulo(new Variable('k'),
                new Variable('i')), new Literal(0));
        loop2.addIntruction(conditional);
        conditional.addIntruction(new Assignment('p', new Literal(0)));

        Conditional conditional2 = new CondEqual(new Variable('p'), new Literal(1));
        loopInterior.addIntruction(loop2);
        loopInterior.addIntruction(conditional2);
        conditional2.addIntruction(new Print(new Variable('k')));

        loop.addIntruction(loopInterior);
        program.addIntruction(loop);
        Debugger debugger = new Debugger();
        int i = 0;
        SingleInstruction next = program.nextSingleInstruction(debugger);
        while (next != null && i < 1000){
            next.execute();
            String next2 = debugger.nextInstruction.toString();
            next = program.nextSingleInstruction(debugger);
            i++;
        }
        System.out.println("----------------\n");
        String next2 = debugger.nextInstruction.toString();
        // FIXME nextInstruction is null
        System.out.println(next2);
        System.out.println("----------------\n");
        System.out.println(next.display(0).toString());

    }

    public void setNextInstruction(Instruction next){
        nextInstruction = next;
    }
    public Instruction getNextInstruction(){
        return nextInstruction;
    }
}
