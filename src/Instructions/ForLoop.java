package Instructions;

import Execution.Debugger;
import Wyrazenia.Literal;
import Wyrazenia.Expression;

public class ForLoop extends InstructionWithScope {
    private Declaration declaration;
    protected Expression expression;
    protected int repetitions;
    private int loopCounter;
    private boolean initialized;
    final private char variableName;
    public ForLoop(InstructionWithScope instr, char variableName, Expression expression){
        // assumption: loop has to be inside a block or another loop
        super(instr.innerScope); // shadowing the parent scope
        initialized = false;
        loopCounter = 0;
        this.variableName = variableName;
        this.declaration = new Declaration(variableName, new Literal(0)); // start from 0
        this.declaration.parentScope = innerScope;
        this.expression = expression;
    }

    @Override
    public void execute(){
        repetitions = expression.evaluate(innerScope);
        declaration.execute();
        for (int i = 0; i< repetitions; i++){
            innerScope.set(variableName, i);
            instructions.execute();
            loopCounter++;
        }
    }

    @Override
    public Instruction nextInstruction(){
        if(initialized == false){
            initialized = true;
            repetitions = expression.evaluate(innerScope);
            return declaration;
        }
        if(loopCounter < repetitions) {
            Instruction next = instructions.nextInstruction();
            if (next == null) { // we actually reached the end of the loop
                loopCounter++;
                instructions.reset();
                return instructions.nextInstruction();
            }
            else
                return next;
        }
        else
            return null;
    }


    @Override
    public SingleInstruction nextSingleInstruction(Debugger debugger){
        if(initialized == false){
            initialized = true;
            repetitions = expression.evaluate(innerScope);
            debugger.setNextInstruction(instructions.firstInstruction());
            return declaration;
        }
        if(loopCounter < repetitions) {
            SingleInstruction next = instructions.nextSingleInstruction(debugger);
            if(next == null) {
                loopCounter++;
                if(loopCounter < repetitions)
                    innerScope.set(variableName, loopCounter); // setting the variable is not a part of the loop
                instructions.reset();
                return nextSingleInstruction(debugger);
            }
            if(debugger.getNextInstruction() == null){
                debugger.setNextInstruction(instructions.firstInstruction());
            }
            return next;
        }
        else{
            initialized = false; // loop reset
            loopCounter = 0;
            return null;
        }
    }

    @Override
    public String toString(){
        return "FOR: " + variableName + " " + expression.toString()  + "{" + '\n' + instructions.toString() + "}" + '\n';
    }

}
