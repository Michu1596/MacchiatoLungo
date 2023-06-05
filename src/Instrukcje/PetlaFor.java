package Instrukcje;

import Wykonanie.Debugger;
import Wyrazenia.Literal;
import Wyrazenia.Wyrazenie;

public class PetlaFor extends InstrukcjaZWartosciowaniem{
    private Deklaracja deklaracja;
    protected Wyrazenie wyrazenie;
    protected int powtorzenia;
    private int licznikPetli;
    private boolean zainicjowano;
    //private boolean policzonoWyrazenie;
    final private char nazwaZmiennej;
    public PetlaFor(InstrukcjaZWartosciowaniem instr, char nazwaZmiennej, Wyrazenie wyrazenie){
        // zakladam że petla musi byc w bloku albo innej petli
        super(instr.wartWewnetrzne); // przyslaniecie zmiennych
        zainicjowano = false;
        licznikPetli = 0;
        this.nazwaZmiennej = nazwaZmiennej;
        this.deklaracja = new Deklaracja(nazwaZmiennej, new Literal(0)); // zaczyanmy od 0
        this.deklaracja.wartNadrzedne = wartWewnetrzne;
        this.wyrazenie = wyrazenie;

        //Przypisanie inkrementacja = new Przypisanie(nazwaZmiennej, new Dodawanie(new Zmienna(nazwaZmiennej),
        //        new Literal(1)));
        //inkrementacja.wartNadrzedne = wartWewnetrzne;
        //this.instrukcje.dodajInstrukcje(inkrementacja); // instrukcja inkrementacji zmiennej sterujacej na
        //poczatku sekwencji instrukcji
    }

    @Override
    public void wykonaj(){
        powtorzenia = wyrazenie.ewaluuj(wartWewnetrzne);
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
            powtorzenia = wyrazenie.ewaluuj(wartWewnetrzne);
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
    public InstrukcjaPojedyncza nastepnaInstrukcjaPojedyncza(Debugger debugger){ //TODO zwrocic wyliczanie wyrazenia na poczatku
        if(zainicjowano == false){
            /*if(policzonoWyrazenie == false) {
                policzonoWyrazenie = true;
                debugger.setKtoraNastepna(deklaracja);
                //powtorzenia = wyrazenie.ewaluuj(wartWewnetrzne);
                return new WyliczenieWartosciFor(this);
            }*/
            zainicjowano = true;
            powtorzenia = wyrazenie.ewaluuj(wartWewnetrzne);
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
        return "FOR: " + nazwaZmiennej + " " + wyrazenie.toString()  + "{" + '\n' + instrukcje.toString() + "}" + '\n';
    }

}
