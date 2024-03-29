package Exceptions;

import Instructions.Instruction;
import Instructions.Scope;

public class MacchiatosError extends RuntimeException{
    public Instruction invalidInstruction;
    public Scope currentValuation;
    public MacchiatosError(Instruction instruction, Scope currentValuation){
        this.invalidInstruction = instruction;
        this.currentValuation = currentValuation;
    }
    public MacchiatosError(){
        ;
    }
    public MacchiatosError(String information){
        super(information);
    }
}
