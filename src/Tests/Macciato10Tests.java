package Tests;

import Instructions.Print;
import Instructions.Assignment;
import Wyrazenia.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Macciato10Tests {
    @Test
    public void print(){
        Print print = new Print(new Literal(50));
        assertEquals("PRINT( 50 )\n", print.toString());
    }
    @Test
    public void assignment(){
        Assignment assignment = new Assignment('n', new Literal(69));
        assertEquals("assignment: n = 69\n", assignment.toString());
    }

    @Test
    public void addition(){
        Addition addition = new Addition(new Variable('n'), new Literal(57));
        assertEquals("n + 57", addition.toString());
    }

    @Test
    public void subtraction(){
        Subtraction subtraction = new Subtraction(new Variable('l'), new Variable('k'));
        assertEquals("l - k", subtraction.toString());
    }

    @Test
    public void division(){
        Division division = new Division(new Literal(21), new Literal(37));
        assertEquals("21 / 37", division.toString());
    }

    @Test
    public void modulo(){
        Modulo mod = new Modulo(new Literal(4), new Literal(20));
        assertEquals("4 % 20", mod.toString());
    }

    @Test
    public void variable(){
        Variable variable = new Variable('k');
        assertEquals("k" , variable.toString());
    }

    @Test
    public void multiplication(){
        Multiplication multiplication = new Multiplication(new Literal(1), new Literal(1));
        assertEquals("1 * 1", multiplication.toString());
    }
}
