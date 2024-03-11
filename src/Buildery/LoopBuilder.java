package Buildery;

import Instrukcje.ForLoop;
import Wyrazenia.Expression;

/**
 * loop has to be inside a block
 */
public class LoopBuilder extends Builder{
    protected ForLoop loop;
    public LoopBuilder(Builder outerScope, char variableName, Expression exp){
        super(outerScope);
        loop = new ForLoop(scopesNesting.peek(), variableName, exp);
        instructionNesting.peek().addIntruction(loop);
        instructionNesting.push(loop);
        scopesNesting.push(loop);
    }

    @Override
    public Builder closeScope() {
        scopesNesting.pop();
        instructionNesting.pop();
        return parent;
    }
}
