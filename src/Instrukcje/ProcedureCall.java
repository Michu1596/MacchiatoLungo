package Instrukcje;


import Wyjatki.InvalidProcedureArgument;
import Wykonanie.Debugger;
import Wyrazenia.Literal;
import Wyrazenia.Expression;

import java.util.ArrayList;

import java.util.List;

/**
 * When debugger encounters a ProcedureCall instruction, it immediately enters the procedure and gains access to the
 * valuation visible in the block in which the procedure was declared
 */
public class ProcedureCall extends complexInstruction {
    protected String procedureName;
    protected List<Expression> arguments;
    protected Procedure procedure;
    protected boolean initialized;
    // used during debugging. If the value is not set and a call occurs, the procedure should be passed the arguments


    /**
     *  Instruction ProcedureCall has to know if the procedure is available in its scope
     * @param procedureName procedure name
     * @param arguments list of expressions that are the arguments of the procedure
     * @param scope in which instruction's scope the procedure is declared
     */
    public ProcedureCall(String procedureName, List<Expression> arguments, complexInstruction scope){
        initialized = false;
        this.procedureName = procedureName;
        procedure = scope.getProcedure(procedureName);
        if (procedure.getNumberOfParameters() != arguments.size())
            throw new InvalidProcedureArgument();
        this.arguments = arguments;
    }
    public ProcedureCall(String procedureName, complexInstruction scope){
        initialized = false;
        this.procedureName = procedureName;
        procedure = scope.getProcedure(procedureName);
        if (procedure.getNumberOfParameters() != 0)
            throw new InvalidProcedureArgument();
    }

    /**
     * method giving access to the next instruction in the procedure. At the time of calling, it initializes the values
     * of the procedure's arguments. It calculates their values and creates a list of Literals that is passed to the
     * procedure. This is done because we want to pass the procedure arguments by value and we need a mechanism to force
     * their copying.
     *
     * @param debugger
     * @return
     */

    @Override
    public SingleInstruction nextSingleInstruction(Debugger debugger){
        if(!initialized){
            List<Expression> expressionsValues = new ArrayList<>();
            for(Expression expression : arguments)
                // makes a list of literals containing the values of the expressions calculated at the time of the call
                expressionsValues.add(new Literal(expression.evaluate(parentScope)));
            procedure.setArguments(expressionsValues);
        }
        Instruction nextInstruction = debugger.getNextInstruction();
        SingleInstruction instr = procedure.nextSingleInstruction(debugger);
        if(debugger.getNextInstruction() == null)
            // if we reach the end of the procedure, the next instruction is the one that follows the call
            debugger.setNextInstruction(nextInstruction);
        if(instr == null) // if we reach the end of the procedure, we start from the beginning
            initialized = false;
        return instr;
    }
    @Override
    public Instruction nextInstruction(){
        return instructions.nextInstruction();
    }
    @Override
    public void execute(){
        procedure.setArguments(arguments);
        procedure.execute();
    }
    @Override
    public String toString(){
        if (arguments != null)
            return procedureName + "( " + arguments.toString() + " )";
        else
            return procedureName + "()\n";
    }
}
