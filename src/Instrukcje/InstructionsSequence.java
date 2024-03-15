package Instrukcje;

import Wykonanie.Debugger;

public class InstructionsSequence {
    private Instruction[] instructions;
    private int instructionCounter;
    private int currentInstruction; // currently executed instruction
    InstructionsSequence(){
        instructions = new Instruction[1];
        currentInstruction = 0;
    }

    public void addInstruction(Instruction instr){
        if(instructionCounter == instructions.length) {
            Instruction[] newInstr = new Instruction[instructionCounter * 2];
            for(int i = 0; i< instructions.length; i++)
                newInstr[i] = instructions[i];
            instructions = newInstr;
        }
        instructions[instructionCounter] = instr;
        instructionCounter++;
    }
    public void execute() {
        if(currentInstruction == instructionCounter)
            currentInstruction = 0;
        for(int i = currentInstruction; i < instructionCounter; i++) {
            instructions[i].execute();
            currentInstruction++;
        }
    }
    public void reset(){
        currentInstruction = 0;
    }
    public Instruction nextInstruction(){
        if(currentInstruction == instructionCounter) { // returns null for the empty sequence
            currentInstruction = 0; // start from the beginning
            return null;
        }
        currentInstruction++;
        return instructions[currentInstruction - 1];
    }

    public Instruction firstInstruction(){
        return instructions[0];
    }


    public SingleInstruction nextSingleInstruction(Debugger debugger){
        if(currentInstruction == instructionCounter) {
            currentInstruction = 0; // start from the beginning
            debugger.setNextInstruction(null);
            return null;
        }
        if (currentInstruction + 1 != instructionCounter) {
            debugger.setNextInstruction(instructions[currentInstruction + 1]);
        }
        else {
            debugger.setNextInstruction(null);
        }
        SingleInstruction next = instructions[currentInstruction].nextSingleInstruction(debugger);
        if(next == null) {
            currentInstruction++;
            return nextSingleInstruction(debugger); // tail recursion
        }
        return next;
    }

    @Override
    public String toString() {
        String instr = "";
        for (int i = 0; i < instructionCounter; i++)
            instr += instructions[i].toString();
        return instr;
    }
}
