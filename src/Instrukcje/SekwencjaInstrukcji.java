package Instrukcje;

import Wykonanie.Debugger;

public class SekwencjaInstrukcji {
    private Instrukcja[] instrukcje;
    private int ileInstrukcji;
    private int aktualnaInstrukcja; //aktualnie wykonywana
    SekwencjaInstrukcji(){
        instrukcje = new Instrukcja[1];
        aktualnaInstrukcja = 0;
    }

    public void dodajInstrukcje(Instrukcja instr){
        if(ileInstrukcji == instrukcje.length) {
            Instrukcja[] nowa = new Instrukcja[ileInstrukcji * 2];
            for(int i=0; i<instrukcje.length; i++)
                nowa[i] = instrukcje[i];
            instrukcje = nowa;
        }
        instrukcje[ileInstrukcji] = instr;
        ileInstrukcji++;
    }
    public void wykonaj() {
        if(aktualnaInstrukcja == ileInstrukcji)
            aktualnaInstrukcja = 0;
        for(int i = aktualnaInstrukcja; i < ileInstrukcji; i++) {
            instrukcje[i].wykonaj();
            aktualnaInstrukcja++;
        }
    }
    public void zeruj(){
        aktualnaInstrukcja = 0;
    }
    public Instrukcja nastepnaInstrukcja(){
        if(aktualnaInstrukcja == ileInstrukcji) { //zwraca null dla pustej sekwencji
            aktualnaInstrukcja = 0; //zaczynamy od poczatku
            return null;
        }
        aktualnaInstrukcja++;
        return instrukcje[aktualnaInstrukcja - 1];
    }

    public Instrukcja pierwszaInstrukcja(){
        return instrukcje[0];
    }


    public InstrukcjaPojedyncza nastepnaInstrukcjaPojedyncza(Debugger debugger){
        if(aktualnaInstrukcja == ileInstrukcji) {
            aktualnaInstrukcja = 0; //zaczynamy od poczatku
            debugger.setKtoraNastepna(null);
            return null;
        }
        if (aktualnaInstrukcja + 1 != ileInstrukcji) {
            debugger.setKtoraNastepna(instrukcje[aktualnaInstrukcja + 1]);
        }
        else {
            debugger.setKtoraNastepna(null);
        }
        //zabezpiecznie na wypadek null
        InstrukcjaPojedyncza nastepna = instrukcje[aktualnaInstrukcja].nastepnaInstrukcjaPojedyncza(debugger);
        if(nastepna == null) {
            aktualnaInstrukcja++;
            return nastepnaInstrukcjaPojedyncza(debugger); // rekurencja ogonowa
        }
        return nastepna;
    }

    @Override
    public String toString() {
        String instr = "";
        for (int i = 0; i < ileInstrukcji; i++)
            instr += instrukcje[i].toString();
        return instr;
    }
}
