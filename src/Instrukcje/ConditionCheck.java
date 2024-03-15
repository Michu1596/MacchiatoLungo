package Instrukcje;

public class ConditionCheck extends SingleInstruction {
    final protected Conditional toCheck;
    public ConditionCheck(Conditional instr){
        toCheck = instr;
    }

    @Override
    public void execute(){
        toCheck.check();
    }
    @Override
    public String toString(){
        return toCheck.toString();
    }
    @Override
    public Scope display(int depth){
        return toCheck.display(depth);
    }
}
