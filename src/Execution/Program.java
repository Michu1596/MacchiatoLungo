package Execution;
import java.io.PrintWriter;
import java.util.*;

import Instructions.Instruction;
import Instructions.SingleInstruction;
import Instructions.Scope;
import SupportClasses.ProcedureVisibilityScope;
import Exceptions.MacchiatosError;

public class Program {
    protected Instruction program;
    protected SingleInstruction nextSingleInstruction;
    public Program(Instruction program){
        this.program = program;
    }

    public void executionWithoutDebug(){

        try {
            program.execute();
        }
        catch (MacchiatosError e){
            System.out.println("Error encountered: " + e.getClass() + e);
        }
    }

    protected void cont(Instruction next, Debugger debugger){
        if(next == null){
            System.out.println("Program has finished");
            return;
        }
        while (next != null){
            next.execute();
            next = program.nextSingleInstruction(debugger);
        }

    }
    protected boolean step(int steps, Debugger debugger){
        int i = 0;
        Debugger previousDebugger = new Debugger();
        while (i < steps && nextSingleInstruction != null){
            try {
                nextSingleInstruction.execute();
                // we save the state of the next instruction to display it, not the next next instruction
                previousDebugger.setNextInstruction(debugger.getNextInstruction());
                nextSingleInstruction = program.nextSingleInstruction(debugger);
                i++;
            }
            catch (MacchiatosError e){
                System.out.println("Exception encountered");
                System.out.println(e);
                nextSingleInstruction = null; // execution has ended
            }
        }
        if(nextSingleInstruction == null){
            System.out.println("Program finished before the end of the steps");
            return false;
        }
        System.out.println("Next instruction:");
        System.out.println(previousDebugger.getNextInstruction().toString());
        return true;
    }
    public void executionWithDebug(){
        Debugger debug = new Debugger();
        Scanner sc= new Scanner(System.in);
        nextSingleInstruction = program.nextSingleInstruction(debug);

        boolean petla = true;
        while (petla) {
            System.out.print("Enter command: ");
            String command = sc.nextLine();
            switch (command.charAt(0)) {
                case 'c': {
                    cont(nextSingleInstruction, debug);
                    petla = false;
                    break;
                }
                case 'e': {
                    System.out.println("Debug ended");
                    petla = false;
                    break;
                }
                case 'd': {
                    command = command.substring(1);
                    command = command.trim(); // trimming spaces
                    Scope toView = nextSingleInstruction.display(Integer.parseInt(command));
                    if(toView == null){
                        System.out.println("Parameter to big for d command");
                    }
                    else
                        System.out.println(toView.toString());
                    break;
                }
                case 's': {
                    command = command.substring(1);
                    command = command.trim();
                    if (Integer.parseInt(command) < 1)
                        System.out.println("step parameter must be a positive integer");
                    else
                        petla = step(Integer.parseInt(command), debug);
                    break;
                }
                case 'm': {
                    String outputFileName = command.substring(1);
                    outputFileName = outputFileName.trim();
                    try (PrintWriter outputFile = new PrintWriter(outputFileName, "UTF-8")
                    )
                    {
                        Scope toView = nextSingleInstruction.display(0);
                        ProcedureVisibilityScope procedures = nextSingleInstruction.getProcedureVisibilityScope();
                        if(procedures != null)
                            outputFile.println(toView.toString() + '\n' + procedures.toString());
                        else
                            outputFile.println(toView.toString());

                    }catch (Exception e) {
                        System.out.println("output file can not be created");
                        throw new RuntimeException();
                    }
                }
            }
        }
    }
}
