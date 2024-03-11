package Testy;

import Instrukcje.Print;
import Instrukcje.Przypisanie;
import Wyrazenia.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Macciato10Testy {
    @Test
    public void print(){
        Print print = new Print(new Literal(50));
        assertEquals("PRINT( 50 )\n", print.toString());
    }
    @Test
    public void przypisanie(){
        Przypisanie przyps = new Przypisanie('n', new Literal(69));
        assertEquals("PRZYPISANIE: n = 69\n", przyps.toString());
    }

    @Test
    public void dodawanie(){
        Addition dod = new Addition(new Variable('n'), new Literal(57));
        assertEquals("n + 57", dod.toString());
    }

    @Test
    public void odejmowanie(){
        Subtraction od = new Subtraction(new Variable('l'), new Variable('k'));
        assertEquals("l - k", od.toString());
    }

    @Test
    public void dzielenie(){
        Division dz = new Division(new Literal(21), new Literal(37));
        assertEquals("21 / 37", dz.toString());
    }

    @Test
    public void modulo(){
        Modulo mod = new Modulo(new Literal(4), new Literal(20));
        assertEquals("4 % 20", mod.toString());
    }

    @Test
    public void zmienna(){
        Variable zm = new Variable('k');
        assertEquals("k" , zm.toString());
    }

    @Test
    public void mnozenie(){
        Mnozenie mn = new Mnozenie(new Literal(1), new Literal(1));
        assertEquals("1 * 1", mn.toString());
    }
}
