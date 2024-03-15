package Builders;

import Instructions.*;
import Wyjatki.MacchiatosError;
import Wykonanie.Program;
import Wyrazenia.Expression;

import java.util.List;
import java.util.Stack;

/**
 *  Builder class contains all methods needed to create a program. The implementation of the methods is provided by
 *  the appropriate subclasses. Calling the wrong method is possible but results in an exception being thrown. Some
 *  methods are common to all builders, so they have been implemented here
 */
public abstract class Builder {
    protected Stack<complexInstruction> instructionNesting; // represents the hierarchy of nested instructions
    protected Stack<InstructionWithScope> scopesNesting; // represents the hierarchy of nested scopes
    protected Stack<Block> proceduresVisibilityNesting;
    protected Builder parent; //pole uzupelniane w momencie dodawania instrukcji zlozonej do jakiegos zakrsu
    // widocznosci; wartosc ta jest zwracana w momencie zamkniecia zakresu widocznosci.
    // variable set when instruction is added to a scope; returned when the scope is closed

    public Builder(){
        instructionNesting = new Stack<>();
        scopesNesting = new Stack<>();
        proceduresVisibilityNesting = new Stack<>();
        parent = null;
    }

    /**
     * constructor creating a shallow copy of the parent instruction builder. It allows access to the hierarchy of nested
     * instructions, valuations, and procedure visibility. We use it by calling methods starting with "open"
     * @param program parent instruction. The currently considered instruction is within its scope
     */
    public Builder(Builder program){
        instructionNesting = program.instructionNesting;
        scopesNesting = program.scopesNesting;
        proceduresVisibilityNesting = program.proceduresVisibilityNesting;
        parent = program;
    }

    /**
     * add Print instruction to the current instruction
     * @param exp expression to be printed
     * @return
     */
    public Builder print(Expression exp){
        complexInstruction scope = instructionNesting.peek();
        scope.addIntruction(new Print(exp));
        return this;
    }

    /**
     * add assignment instruction to the current instruction
     * @param variable variable to which we assign the value
     * @param exp2 expression to be assigned
     * @return
     */

    public Builder assignment(char variable, Expression exp2){
        complexInstruction scope = instructionNesting.peek();
        scope.addIntruction(new Przypisanie(variable, exp2));
        return this;
    }

    /**
     * adds a procedure call instruction
     * @param name procedure name to be called
     * @param args list of expressions to be passed as arguments
     * @return
     */
    public Builder procedureCall(String name, List<Expression> args){
        complexInstruction scope = instructionNesting.peek();
        complexInstruction proceduresVisibility = proceduresVisibilityNesting.peek();
        scope.addIntruction(new ProcedureCall(name, args, proceduresVisibility));
        return this;
    }

    /**
     * adds a procedure call instruction without arguments
     * @param name
     * @return
     */
    public Builder procedureCall(String name){
        complexInstruction scope = instructionNesting.peek();
        complexInstruction proceduresVisibility = proceduresVisibilityNesting.peek();
        scope.addIntruction(new ProcedureCall(name, proceduresVisibility));
        return this;
    }

    /**
     *  controlling all conditional instructions
     * @param cond "<" , ">" , "==" , "<=" or ">="
     * @param exp1 left expression
     * @param exp2 right expression
     */
    public IfBuilder openIfInstruction(String cond, Expression exp1, Expression exp2){
        return new IfBuilder(this, cond, exp1, exp2);
    }

    public LoopBuilder openLoopInstruction(char steeringVar, Expression iniExp){ // initializing expression
        return new LoopBuilder(this, steeringVar, iniExp);
    }

    public BlockBuilder openBlock(){
        return new BlockBuilder(this);
    }


    // Following methods must be overridden

    /**
     * closes the scope of the currently considered instruction; like '}' in the code
     * @return parent builder
     */
    public Builder closeScope(){
        throw new MacchiatosError("program build error");
    }

    /**
     * Creates a new procedure (with arguments) visible in the block in which it was declared; can only be called on
     * @param name procedure name
     * @param args arguments taken by the procedure
     * @return created procedure builder
     */
    public ProcedureBuilder openProcedure(String name, char[] args){
        throw new MacchiatosError("opening procedure is only possible in a block");
    }
    /**
     * Creeates a new procedure (without arguments) visible in the block in which it was declared; can only be called on
     * BlockBuilder
     * @param name procedure name
     * @return created procedure builder
     */
    public ProcedureBuilder openProcedure(String name){
        throw new MacchiatosError("opening procedure is only possible in a block");
    }

    /**
     * Declares a variable; can only be called on BlockBuilder or ProcedureBuilder
     * @param name variable name
     * @param exp expression to be assigned to the variable
     * @return builder of instruction in which the variable was declared
     */
    public Builder declareVariable(char name, Expression exp){
        throw new MacchiatosError("Variable declaration is only possible in a block or a procedure");
    }

    /**
     * method creating a program
     * @return program
     */
    public Program build(){
        throw new MacchiatosError("building a program is only possible in a ProgramBuilder");
    }
    public complexInstruction getInstruction(){
        throw new MacchiatosError("program build error");
    }
}
