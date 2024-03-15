package Instructions;

import Wykonanie.Debugger;
import Wyrazenia.Expression;

public abstract class Conditional extends complexInstruction {
    protected Expression exp1;
    protected Expression exp2;
    protected boolean conditionChecked;
    protected boolean conditionOccurred;
    protected InstructionsSequence elseCase;
    protected Conditional(){
        super();
    }
    protected Conditional(Expression exp1, Expression exp2){
        super();
        elseCase = new InstructionsSequence();
        conditionChecked = false;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }
    protected abstract void check();

    @Override
    public void execute(){
        check();
        if(conditionOccurred)
            instructions.execute();
        else
            elseCase.execute();
    }

    @Override
    public Instruction nextInstruction(){
        if(!conditionChecked) {
            check();
            return new ConditionCheck(this);
        }
        Instruction next;
        if (conditionOccurred)
            next = instructions.nextInstruction();
        else
            next = elseCase.nextInstruction();
        if(next == null) // as we reached the end, we start from the beginning
            conditionChecked = false;
        return next;
    }
    @Override
    public SingleInstruction nextSingleInstruction(Debugger debugger){
        if(!conditionChecked) {
            check();
            return new ConditionCheck(this);
        }
        SingleInstruction next;
        if (conditionOccurred)
            next = instructions.nextSingleInstruction(debugger);

        else
            next = elseCase.nextSingleInstruction(debugger);

        if(next == null) // as we reached the end, we start from the beginning
            conditionChecked = false;
        return next;
    }
    public void addElseInstruction(Instruction instr) {
        instr.parentScope = parentScope;
        elseCase.addInstruction(instr);
    }
}
