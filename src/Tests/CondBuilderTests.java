package Tests;
import Builders.BlockBuilder;
import Instructions.*;
import Wyrazenia.Literal;
import Wyrazenia.Variable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CondBuilderTests {
    @Test
    public void CondEquals(){
        complexInstruction block = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .assignment('x', new Literal(561))
                .openIfInstruction("==", new Variable('x'), new Literal(561))
                .print(new Variable('x'))
                .closeScope()
                .getInstruction();

        Block template = new Block();
        template.addDeclaration('x', new Literal(7));
        template.addIntruction(new Assignment('x', new Literal(561)));
        Conditional conditional = new CondEqual(new Variable('x'), new Literal(561));
        conditional.addIntruction(new Print(new Variable('x')));
        template.addIntruction(conditional);

        assertEquals(template.toString(), block.toString());
    }

    @Test
    public void CondLess(){
        complexInstruction block = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .assignment('x', new Literal(561))
                .openIfInstruction("<", new Variable('x'), new Literal(561))
                .print(new Variable('x'))
                .closeScope()
                .getInstruction();

        Block template = new Block();
        template.addDeclaration('x', new Literal(7));
        template.addIntruction(new Assignment('x', new Literal(561)));
        Conditional conditional = new CondLess(new Variable('x'), new Literal(561));
        conditional.addIntruction(new Print(new Variable('x')));
        template.addIntruction(conditional);

        assertEquals(template.toString(), block.toString());
    }

    @Test
    public void CondLessEquals(){
        complexInstruction block = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .assignment('x', new Literal(561))
                .openIfInstruction("<=", new Variable('x'), new Literal(561))
                .print(new Variable('x'))
                .closeScope()
                .assignment('y', new Literal(25))
                .getInstruction();

        Block template = new Block();
        template.addDeclaration('x', new Literal(7));
        template.addIntruction(new Assignment('x', new Literal(561)));
        Conditional conditional = new CondLessEq(new Variable('x'), new Literal(561));
        conditional.addIntruction(new Print(new Variable('x')));
        template.addIntruction(conditional);
        template.addIntruction(new Assignment('y', new Literal(25)));

        assertEquals(template.toString(), block.toString());
    }

    @Test
    public void CondGreaterEquals(){
        complexInstruction block = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .assignment('x', new Literal(561))
                .openIfInstruction(">=", new Variable('x'), new Literal(561))
                .print(new Variable('x'))
                .closeScope()
                .assignment('y', new Literal(25))
                .getInstruction();

        Block template = new Block();
        template.addDeclaration('x', new Literal(7));
        template.addIntruction(new Assignment('x', new Literal(561)));
        Conditional conditional = new CondGreaterEq(new Variable('x'), new Literal(561));
        conditional.addIntruction(new Print(new Variable('x')));
        template.addIntruction(conditional);
        template.addIntruction(new Assignment('y', new Literal(25)));

        assertEquals(template.toString(), block.toString());
    }

    @Test
    public void CondGreater(){
        complexInstruction block = new BlockBuilder()
                .declareVariable('x', new Literal(7))
                .assignment('x', new Literal(561))
                .openIfInstruction(">", new Variable('x'), new Literal(561))
                .print(new Variable('x'))
                .closeScope()
                .assignment('y', new Literal(25))
                .getInstruction();

        Block template = new Block();
        template.addDeclaration('x', new Literal(7));
        template.addIntruction(new Assignment('x', new Literal(561)));
        Conditional warunkowa = new CondGreater(new Variable('x'), new Literal(561));
        warunkowa.addIntruction(new Print(new Variable('x')));
        template.addIntruction(warunkowa);
        template.addIntruction(new Assignment('y', new Literal(25)));

        assertEquals(template.toString(), block.toString());
    }


}
