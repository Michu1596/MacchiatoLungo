package Instructions;

import SupportClasses.ProcedureVisibilityScope;
import Exceptions.DoubleDeclaration;
import Execution.Debugger;
import Wyrazenia.Expression;

import java.util.*;

/**
 * In the moment of declaration of a procedure in a block, the procedure gains access to the valuation of the block. This
 * translates into the use of Static variable binding
 */
public class Procedure extends InstructionWithScope {
    protected Set<Character> args;    
    // used to check if the same variable is not declared twice
    protected Set<Character> declaredVariables;
    // variables from the declaration sequence in the procedure; the arguments are not included
    protected List<Declaration> argsValues;
    // variable declarations corresponding to the arguments at the beginning of the instruction list. Initially, the
    // expression is null. It is filled in when the procedure is called

    protected ProcedureVisibilityScope innerProcedures;

    /**
     * Procedure has to be inside a block and requires information about it
     * @param outerScope visibility scope in which the procedure is declared
     * @param args sequence of variables that are the arguments of the procedure
     */
    public Procedure(InstructionWithScope outerScope, char args[]){
        super(outerScope.innerScope);
        this.args = new LinkedHashSet<Character>(); // we want to keep the order of adding elements
        this.argsValues = new ArrayList<Declaration>();
        declaredVariables = new HashSet<Character>();

        // we want to make sure that the same variable is not declared twice
        for (char arg : args) {
            if(this.args.contains(arg))
                throw new DoubleDeclaration(arg);
            this.args.add(arg);
            Declaration declaration = new Declaration(arg, null);
            // null is replaced by the actual expression when the procedure is called
            argsValues.add(declaration);
            declaration.parentScope = innerScope;
            instructions.addInstruction(declaration);
            this.args.add(arg);
        }

    }

    /**
     * parameterless procedure constructor
     * @param outerScope  visibility scope in which the procedure is declared
     */
    public Procedure(InstructionWithScope outerScope){
        super(outerScope.innerScope);
        this.args = new LinkedHashSet<Character>(); //we want to keep the order of adding elements
        this.argsValues = new ArrayList<Declaration>();
        declaredVariables = new HashSet<Character>();


    }

    /**
     * Passing arguments to the procedure is done by providing the procedure with a list of literals, which are the
     * result of evaluating expressions at the moment of calling the procedure.
     * @param expressions list of expressions to be passed as arguments
     */
    public void setArguments(List<Expression> expressions){
        Iterator<Expression> expressionIterator = expressions.iterator();
        Iterator<Declaration> argumentDeclaration = argsValues.iterator();
        while (argumentDeclaration.hasNext()){
            Declaration argument = argumentDeclaration.next();
            argument.setExpression(expressionIterator.next());
            argument.procedureVisibilityScope = procedureVisibilityScope;
        }
    }

    /**
     * this method should be called before adding any instructions
     * @param variableName
     * @param value
     */
    public void addVariable(char variableName, Expression value){
        if(declaredVariables.contains(variableName))
            throw new DoubleDeclaration(variableName);
        declaredVariables.add(variableName);
        Declaration declaration = new Declaration(variableName, value);
        declaration.parentScope = innerScope;
        instructions.addInstruction(declaration);
    }

    public void addProcedure(String procedureNAme, Procedure procedure){
        innerProcedures.declareProcedure(procedureNAme, procedure);
        // double declaration is handled in this method
        procedure.procedureVisibilityScope = innerProcedures;
    }

    /**
     * method useful for printing the header of the procedure. It prints the list of parameters
     * @return one-letter names of the parameters
     */
    public String getArgs(){
        return args.toString();
    }

    public int getNumberOfParameters(){
        return args.size();
    }

    @Override
    public SingleInstruction nextSingleInstruction(Debugger debugger){
        return instructions.nextSingleInstruction(debugger);
    }
    @Override
    public Instruction nextInstruction(){
        return instructions.nextInstruction();
    }
    @Override
    public void execute(){
        instructions.execute();
    }
    @Override
    public String toString(){
        if(args != null)
            return getArgs() + '\n' + instructions.toString();
        else
            return instructions.toString();
    }
}
