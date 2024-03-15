package Instructions;

public abstract class complexInstruction extends Instruction {
    protected InstructionsSequence instructions;

    protected complexInstruction(){
        instructions = new InstructionsSequence();
    }

    public Procedure getProcedure(String nazwa){
        return procedureVisibilityScope.get(nazwa);
    }
    public void addIntruction(Instruction instr){
        instr.parentScope = parentScope;
        instr.procedureVisibilityScope = procedureVisibilityScope;
        instructions.addInstruction(instr);
    }
}
