package Instructions;

public abstract class InstructionWithScope extends complexInstruction {
    final protected Scope innerScope;
    protected InstructionWithScope(Scope parent){ // for nested blocks
        super(); // makes instructions sequence
        innerScope = new Scope(parent);
    }
    protected InstructionWithScope(){ // for non nested blocks
        super();
        innerScope = new Scope();
    }
    @Override
    public void addIntruction(Instruction instr){
        instr.parentScope = innerScope; // procedures have static scope
        instr.procedureVisibilityScope = procedureVisibilityScope;
        instructions.addInstruction(instr);
    }
    @Override
    public Scope display(int depth){ // moze null dac
        return innerScope.valuation(depth);
    }
}
