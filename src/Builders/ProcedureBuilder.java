package Builders;

import Instructions.Block;
import Instructions.complexInstruction;
import Instructions.Procedure;
import Wyjatki.DoubleDeclaration;
import Wyrazenia.Expression;

public class ProcedureBuilder extends  Builder{ //dziedziczenie wynika stad ze procedura musi byc wewnatrz bloku
    Procedure procedure;
    public ProcedureBuilder(BlockBuilder outerScope, Block block, String name, char[] args){
        super(outerScope);
        procedure = new Procedure(block, args);
        block.addProcedure(name, procedure);
        instructionNesting.push(procedure);
        scopesNesting.push(procedure);
    }
    public ProcedureBuilder(BlockBuilder outerScope, Block block, String name){
        super(outerScope);
        procedure = new Procedure(block);
        block.addProcedure(name, procedure);
        instructionNesting.push(procedure);
        scopesNesting.push(procedure);
    }
    public ProcedureBuilder declareVariable(char name, Expression exp){
        try {
            procedure.addVariable(name, exp);
        }catch (DoubleDeclaration e){
            System.out.println("Variable: " + name + " has been declared in this procedure");
        }
        return this;
    }
    @Override
    public Builder closeScope(){
        instructionNesting.pop();
        scopesNesting.pop();
        return parent;
    }
    @Override
    public complexInstruction getInstruction(){
        return procedure;
    }
}
