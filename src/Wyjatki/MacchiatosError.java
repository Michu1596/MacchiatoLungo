package Wyjatki;

import Instrukcje.Instruction;
import Instrukcje.Scope;

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
    public MacchiatosError(String informacja){
        super(informacja);
    }
}
