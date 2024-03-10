package Buildery;

import Wykonanie.Program;

public class ProgramBuilder extends BlockBuilder {

    @Override
    public Program zbuduj(){
        return new Program(block);
    }
}
