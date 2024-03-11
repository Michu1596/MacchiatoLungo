package Instrukcje;

import Wykonanie.Debugger;
import Wyrazenia.Literal;
import Wyrazenia.Expression;

public class ForLoop extends InstrukcjaZWartosciowaniem{
    private Deklaracja deklaracja;
    protected Expression expression;
    protected int powtorzenia;
    private int licznikPetli;
    private boolean zainicjowano;
    final private char nazwaZmiennej;
    public ForLoop(InstrukcjaZWartosciowaniem instr, char nazwaZmiennej, Expression expression){
        // zakladam że petla musi byc w bloku albo innej petli
        super(instr.wartWewnetrzne); // przyslaniecie zmiennych
        zainicjowano = false;
        licznikPetli = 0;
        this.nazwaZmiennej = nazwaZmiennej;
        this.deklaracja = new Deklaracja(nazwaZmiennej, new Literal(0)); // zaczyanmy od 0
        this.deklaracja.wartNadrzedne = wartWewnetrzne;
        this.expression = expression;
    }

    @Override
    public void wykonaj(){
        powtorzenia = expression.ewaluuj(wartWewnetrzne);
        deklaracja.wykonaj();
        for (int i = 0; i<powtorzenia; i++){
            wartWewnetrzne.set(nazwaZmiennej, i);
            instrukcje.wykonaj();
            licznikPetli++;
        }
    }

    @Override
    public Instrukcja nastepnaInstrukcja(){
        if(zainicjowano == false){
            zainicjowano = true;
            powtorzenia = expression.ewaluuj(wartWewnetrzne);
            return  deklaracja;
        }
        if(licznikPetli < powtorzenia) {
            Instrukcja nastepna = instrukcje.nastepnaInstrukcja();
            if (nastepna == null) { // doszlismy do konca
                licznikPetli++;
                instrukcje.zeruj();
                return instrukcje.nastepnaInstrukcja();
            }
            else
                return nastepna;
        }
        else
            return null;
    }


    @Override
    public InstrukcjaPojedyncza nastepnaInstrukcjaPojedyncza(Debugger debugger){
        if(zainicjowano == false){
            zainicjowano = true;
            powtorzenia = expression.ewaluuj(wartWewnetrzne);
            debugger.setKtoraNastepna(instrukcje.pierwszaInstrukcja());
            return  deklaracja;
        }
        if(licznikPetli < powtorzenia) {
            //zabezpiecznie na wypadek null
            InstrukcjaPojedyncza nastepna = instrukcje.nastepnaInstrukcjaPojedyncza(debugger);
            if(nastepna == null) {
                licznikPetli++;
                if(licznikPetli < powtorzenia)
                    wartWewnetrzne.set(nazwaZmiennej, licznikPetli); // ustawienie zmiennej to nie instrukcja
                instrukcje.zeruj();
                return nastepnaInstrukcjaPojedyncza(debugger);
            }
            if(debugger.getKtoraNastepna() == null){
                debugger.setKtoraNastepna(instrukcje.pierwszaInstrukcja());
            }
            return nastepna;
        }
        else{
            zainicjowano = false; // resetujemy petle
            //policzonoWyrazenie = false;
            licznikPetli = 0;
            return null;
        }
    }

    @Override
    public String toString(){
        return "FOR: " + nazwaZmiennej + " " + expression.toString()  + "{" + '\n' + instrukcje.toString() + "}" + '\n';
    }

}
