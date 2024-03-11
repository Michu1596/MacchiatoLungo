package Testy;
import Buildery.BlockBuilder;
import Instrukcje.*;
import Wyrazenia.Literal;
import Wyrazenia.Zmienna;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class IfBuilderTesty {
    @Test
    public void IfRowne(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .assignment('x', new Literal(561))
                .openIfInstruction("==", new Zmienna('x'), new Literal(561))
                .print(new Zmienna('x'))
                .closeScope()
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        wzor.addIntruction(new Przypisanie('x', new Literal(561)));
        Conditional warunkowa = new CondEqual(new Zmienna('x'), new Literal(561));
        warunkowa.addIntruction(new Print(new Zmienna('x')));
        wzor.addIntruction(warunkowa);

        assertEquals(wzor.toString(), blok.toString());
    }

    @Test
    public void IfMniejsze(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .assignment('x', new Literal(561))
                .openIfInstruction("<", new Zmienna('x'), new Literal(561))
                .print(new Zmienna('x'))
                .closeScope()
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        wzor.addIntruction(new Przypisanie('x', new Literal(561)));
        Conditional warunkowa = new CondLess(new Zmienna('x'), new Literal(561));
        warunkowa.addIntruction(new Print(new Zmienna('x')));
        wzor.addIntruction(warunkowa);

        assertEquals(wzor.toString(), blok.toString());
    }

    @Test
    public void IfMniejszeRowne(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .assignment('x', new Literal(561))
                .openIfInstruction("<=", new Zmienna('x'), new Literal(561))
                .print(new Zmienna('x'))
                .closeScope()
                .assignment('y', new Literal(25))
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        wzor.addIntruction(new Przypisanie('x', new Literal(561)));
        Conditional warunkowa = new CondLessEq(new Zmienna('x'), new Literal(561));
        warunkowa.addIntruction(new Print(new Zmienna('x')));
        wzor.addIntruction(warunkowa);
        wzor.addIntruction(new Przypisanie('y', new Literal(25)));

        assertEquals(wzor.toString(), blok.toString());
    }

    @Test
    public void IfWiekszeRowne(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .assignment('x', new Literal(561))
                .openIfInstruction(">=", new Zmienna('x'), new Literal(561))
                .print(new Zmienna('x'))
                .closeScope()
                .assignment('y', new Literal(25))
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        wzor.addIntruction(new Przypisanie('x', new Literal(561)));
        Conditional warunkowa = new CondGreaterEq(new Zmienna('x'), new Literal(561));
        warunkowa.addIntruction(new Print(new Zmienna('x')));
        wzor.addIntruction(warunkowa);
        wzor.addIntruction(new Przypisanie('y', new Literal(25)));

        assertEquals(wzor.toString(), blok.toString());
    }

    @Test
    public void IfWieksze(){
        complexInstruction blok = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .assignment('x', new Literal(561))
                .openIfInstruction(">", new Zmienna('x'), new Literal(561))
                .print(new Zmienna('x'))
                .closeScope()
                .assignment('y', new Literal(25))
                .getInstruction();

        Block wzor = new Block();
        wzor.addDeclaration('x', new Literal(7));
        wzor.addIntruction(new Przypisanie('x', new Literal(561)));
        Conditional warunkowa = new CondGreater(new Zmienna('x'), new Literal(561));
        warunkowa.addIntruction(new Print(new Zmienna('x')));
        wzor.addIntruction(warunkowa);
        wzor.addIntruction(new Przypisanie('y', new Literal(25)));

        assertEquals(wzor.toString(), blok.toString());
    }


}
