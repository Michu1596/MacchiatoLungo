package Buildery;

import Instrukcje.Block;
import Instrukcje.complexInstruction;
import Instrukcje.Procedura;
import Wyjatki.DoubleDeclaration;
import Wyrazenia.Expresion;

public class ProcedureBuilder extends  Builder{ //dziedziczenie wynika stad ze procedura musi byc wewnatrz bloku
    Procedura procedura;
    public ProcedureBuilder(BlockBuilder zakresZewnetrzny, Block block, String nazwa, char[] argumenty){
        super(zakresZewnetrzny);
        procedura = new Procedura(block, argumenty);
        block.dodajProcedure(nazwa, procedura);
        instructionNesting.push(procedura);
        scopesNesting.push(procedura);
    }
    public ProcedureBuilder(BlockBuilder zakresZewnetrzny, Block block, String nazwa){
        super(zakresZewnetrzny);
        procedura = new Procedura(block);
        block.dodajProcedure(nazwa, procedura);
        instructionNesting.push(procedura);
        scopesNesting.push(procedura);
    }
    public ProcedureBuilder declareVariable(char nazwa, Expresion wyr){
        try {
            procedura.dodajDeklaracje(nazwa, wyr);
        }catch (DoubleDeclaration e){
            System.out.println("Zmienna: " + nazwa + " zostala juz zadeklarowana w tej procedurze");
        }
        return this;
    }
    @Override
    public Builder finishScope(){
        instructionNesting.pop();
        scopesNesting.pop();
        return parent;
    }
    @Override
    public complexInstruction getInstruction(){
        return procedura;
    }
}
