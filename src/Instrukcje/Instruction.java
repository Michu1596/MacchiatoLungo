package Instrukcje;

import KlasyPomocnicze.ProcedureVisibilityScope;
import Wykonanie.Debugger;

public abstract class Instruction {
    protected Scope parentScope;
    protected ProcedureVisibilityScope procedureVisibilityScope;
    public abstract void execute();
    public abstract Instruction nextInstruction();
    public abstract SingleInstruction nextSingleInstruction(Debugger debugger);
    @Override
    public abstract String toString();
    // dispaly is overridden in the class InstructionWithScope
    public Scope display(int depth){ // moze null dac
        return parentScope.valuation(depth);
    }
    public ProcedureVisibilityScope getProcedureVisibilityScope() {
        return procedureVisibilityScope;
    }
}
