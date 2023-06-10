package Buildery;

import Instrukcje.Blok;
import Wykonanie.Program;

import java.util.Stack;

public class ProgramBuilder extends BlokBuilder{

    @Override
    public Program zbuduj(){
        return new Program(blok);
    }
}
