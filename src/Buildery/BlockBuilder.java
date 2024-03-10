package Buildery;

import Instrukcje.Block;
import Instrukcje.complexInstruction;
import Wyjatki.DoubleDeclaration;
import Wyrazenia.Expresion;

/**
 * BlockBuilder class implements the zadeklarujZmienna and rozpocznijProcedure methods - with and without arguments
 */
public class BlockBuilder extends Builder{

    protected Block block;

    /**
     * Constructor allowing to create a block inside another compound instruction
     * @param outerScope
     */
    public BlockBuilder(Builder outerScope){
        super(outerScope);

        if(scopesNesting.isEmpty())
            this.block = new Block();
        else {
            this.block = new Block(scopesNesting.peek());
        }
        instructionNesting.peek().addIntruction(block);
        instructionNesting.push(block);
        scopesNesting.push(block);
        proceduresVisibilityNesting.push(block);
    }

    /**
     * constructor for creating a nested block. It allows for the correct creation of the procedure visibility scope
     * based on the outer block
     * @param outerScope outer visibility scope
     * @param outerBlock outer block in which the new block is declared
     */
    public BlockBuilder(Builder outerScope, Block outerBlock){
        super(outerScope);

        if(scopesNesting.isEmpty())
            this.block = new Block();
        else {
            this.block = new Block(scopesNesting.peek());
        }
        block.connectOuterBlock(outerBlock);
        instructionNesting.peek().addIntruction(block);
        instructionNesting.push(block);
        scopesNesting.push(block);
        proceduresVisibilityNesting.push(block);
    }

    public BlockBuilder(){
        super();
        this.block = new Block();
        instructionNesting.push(block);
        scopesNesting.push(block);
        proceduresVisibilityNesting.push(block);
    }
    @Override
    public BlockBuilder declareVariable(char name, Expresion exp){

        try {
            block.addDeclaration(name, exp);
        }catch (DoubleDeclaration e){
            System.out.println("Variable: " + name + " has been declared in this block");
        }
        return this;
    }

    @Override
    public ProcedureBuilder beginProcedure(String name, char[] args){
        return new ProcedureBuilder(this, block, name, args);
    }
    // argument-less version
    @Override
    public ProcedureBuilder beginProcedure(String name){
        return new ProcedureBuilder(this, block, name);
    }

    /**
     * method allowing to create a nested block
     * @return
     */
    @Override
    public BlockBuilder beginBlock(){
        return new BlockBuilder(this, block);
    }

    @Override
    public Builder finishScope(){
        instructionNesting.pop();
        scopesNesting.pop();
        proceduresVisibilityNesting.pop();
        return parent;
    }
    @Override
    public complexInstruction getInstruction() {
        return block;
    }

}
