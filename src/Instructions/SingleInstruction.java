package Instructions;

import Wykonanie.Debugger;

public abstract class SingleInstruction extends Instruction { // its execute method is called in debugger
    protected boolean executed;
    protected SingleInstruction(){
        executed = false;
    }
    @Override
    public SingleInstruction nextSingleInstruction(Debugger debugger){
        if(executed){
            executed = false;
            return null;
        }
        executed = true;
        return this;
    }
    @Override
    public Instruction nextInstruction(){
        return this;
    }
}
