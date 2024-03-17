package Builders;

import Execution.Program;

public class ProgramBuilder extends BlockBuilder {

    @Override
    public Program build(){
        return new Program(block);
    }
}
