package Testy;

import Instrukcje.Blok;
import Instrukcje.Procedura;
import Instrukcje.WywolanieProcedury;
import Wyjatki.BladMacchiato;
import Wyjatki.NieprawidloweArgumentyProcedury;
import Wyjatki.NiezadeklarowanaProcedura;
import Wyjatki.NiezadeklarowanaZmienna;
import Wyrazenia.Literal;
import Wyrazenia.Zmienna;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static  org.junit.jupiter.api.Assertions.*;

public class WywolanieProceduryTesty {
    protected Blok blok;
    protected Procedura proc;
    protected char[] arg;
    @BeforeEach
    public void init(){
        blok = new Blok();
        arg = new char[] {'a', 'b'};
        proc = new Procedura(blok, arg);
        blok.dodajProcedure("proc", proc);
    }
    @Test
    public void zlaLiczbaArgumentow(){
        assertThrows(BladMacchiato.class, () -> new WywolanieProcedury("proc",
                                                                        List.of(new Literal(25)), blok));
    }
    @Test
    public void procBezArg(){
        Procedura bezArg = new Procedura(blok);
        blok.dodajProcedure("procBezArg", bezArg);
        assertThrows(NieprawidloweArgumentyProcedury.class, () -> new WywolanieProcedury("procBezArg",
                                                    List.of(new Literal(25)), blok));
    }
    @Test
    public void nieistniejacaProcedura(){
        assertThrows(NiezadeklarowanaProcedura.class, () -> new WywolanieProcedury("nieistniejcProcedura", blok));
    }
    @Test
    public void wewnetrzneWywolanie(){
        Blok wewn = new Blok(blok);
        wewn.dodajDeklaracje('x', new Literal(57));
        wewn.dodajDeklaracje('y', new Literal(561));
        wewn.przypnijBlokZewnetrzny(blok);
        WywolanieProcedury wewnWyw = new WywolanieProcedury("proc",
                    List.of(new Zmienna('x'), new Zmienna('y')), wewn);
        wewn.dodajInstrukcje(wewnWyw);
        assertDoesNotThrow(() -> blok.wykonaj());

        WywolanieProcedury zewnWyw = new WywolanieProcedury("proc",
                List.of(new Zmienna('x'), new Zmienna('y')), blok);
        wewn.dodajInstrukcje(wewnWyw);

        assertThrows(NiezadeklarowanaZmienna.class, () -> zewnWyw.wykonaj());
    }
}
