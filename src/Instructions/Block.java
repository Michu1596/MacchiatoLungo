package Instructions;

import SupportClasses.ProcedureVisibilityScope;
import Wykonanie.Debugger;
import Exceptions.DoubleDeclaration;
import Wyrazenia.Expression;

import java.util.*;

public class Block extends InstructionWithScope {
    private Set<Character> declaredVariables;
    protected ProcedureVisibilityScope innerProcedures;
    public Block(){
        super(); // makes new scope
        innerProcedures = new ProcedureVisibilityScope();
        declaredVariables = new HashSet<>();
    }
    public Block(InstructionWithScope instr){ // nested block
        super(instr.innerScope); // shadowing variables
        declaredVariables = new HashSet<>();
        innerProcedures = new ProcedureVisibilityScope();
    }

    // method needed to create a block directly nested in another block
    public void connectOuterBlock(Block instr){ // block as instruction with scope
        innerProcedures = new ProcedureVisibilityScope(instr.innerProcedures);
    }
    public void addDeclaration(char variableName, Expression exp){
        if(declaredVariables.contains(variableName))
            throw new DoubleDeclaration(variableName);
        declaredVariables.add(variableName);
        Declaration declaration = new Declaration(variableName, exp);
        declaration.parentScope = innerScope;
        instructions.addInstruction(declaration);
    }

    public void addProcedure(String procedureName, Procedure procedure){
        innerProcedures.declareProcedure(procedureName, procedure);
        // double declaration handling is in this method
        procedure.procedureVisibilityScope = innerProcedures;
    }
    @Override
    public Procedure getProcedure(String name){
        return innerProcedures.get(name);
    }
    @Override
    public void execute() {
        instructions.execute();
    }

    @Override
    public Instruction nextInstruction(){
        return instructions.nextInstruction();
    }

    @Override
    public SingleInstruction nextSingleInstruction(Debugger debugger){
        return instructions.nextSingleInstruction(debugger);
    }
    @Override
    public void addIntruction(Instruction instr){
        instr.parentScope = innerScope; //
        instr.procedureVisibilityScope = innerProcedures;
        instructions.addInstruction(instr);
    }
    @Override
    public ProcedureVisibilityScope getProcedureVisibilityScope() {
        return innerProcedures;
    }
    @Override
    public String toString(){
        return "BLOCK: { " + '\n' + instructions.toString() + '}' + '\n';
    }
}
