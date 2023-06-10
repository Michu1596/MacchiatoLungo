package Buildery;

import Instrukcje.*;
import Wyjatki.BladMacchiato;
import Wykonanie.Program;
import Wyrazenia.Wyrazenie;

import java.util.List;
import java.util.Stack;

/**
 *  Klasa Builder zawiera wszystkie metody potrzbne do utworzenia programu. Implementacji metod dostarczaja odpowiednie
 *  podklasy. Wywolanie zlej metody jest mozliwe ale skutkuje rzuceniem wyjatku
 */
public abstract class Builder {
    protected Stack<InstrukcjaZlozona> zagniezdzenieInstrukcji; //reprezentuje hierarchie zagniezdzenia instrukcji
    protected Stack<InstrukcjaZWartosciowaniem> zagniezdzenieWartosciowania; //reprezentuje hierarchie zagniezdzenia
    protected Stack<Blok> zagniezdzenieWidocznosciProcedur;
    protected Builder nadrzedny; //pole uzupelniane w momencie dodawania instrukcji zlozonej do jakiegos zakrsu
    // widocznosci; wartosc ta jest zwracana w momencie zamkniecia zakresu widocznosci.

    public Builder(){
        zagniezdzenieInstrukcji = new Stack<>();
        zagniezdzenieWartosciowania = new Stack<>();
        zagniezdzenieWidocznosciProcedur = new Stack<>();
        nadrzedny = null;
    }

    /**
     * konstruktor tworzacy shallow copy buildera instrukcji nadrzednej. Pozwala on otrzymac dostep do hierarchii
     * zagniezdzen instrukcji.
     * @param program instrukcja nadrzedna. W jej zakresie znajduje sie aktualnie rozpatrywna instrukcja
     */
    public Builder(Builder program){
        zagniezdzenieInstrukcji = program.zagniezdzenieInstrukcji;
        zagniezdzenieWartosciowania = program.zagniezdzenieWartosciowania;
        zagniezdzenieWidocznosciProcedur = program.zagniezdzenieWidocznosciProcedur;
        nadrzedny = program;
    }

    /**
     * dodaje instrukcje Print do aktualnej instrukcji
     * @param wyr wyrazenie do wydrukowania
     * @return
     */
    public Builder print(Wyrazenie wyr){
        InstrukcjaZlozona zakres = zagniezdzenieInstrukcji.peek();
        zakres.dodajInstrukcje(new Print(wyr));
        return this;
    }

    /**
     * dodaje instrukcje przypisania do aktualnej instrukcji
     * @param zmienna zmienna do ktorej przypisujemy
     * @param wyr2 wyrazenie jakiego wartosc przypisujemy
     * @return
     */

    public Builder przypisanie(char zmienna, Wyrazenie wyr2){
        InstrukcjaZlozona zakres = zagniezdzenieInstrukcji.peek();
        zakres.dodajInstrukcje(new Przypisanie(zmienna, wyr2));
        return this;
    }

    /**
     * dodaje instrukcje wywolania procedury
     * @param nazwa nazwa procedury do wywolania
     * @param argumenty lista wyrazen zawierajaca argumenty
     * @return
     */
    public Builder wywolanieProcedury(String nazwa, List<Wyrazenie> argumenty){
        InstrukcjaZlozona zakres = zagniezdzenieInstrukcji.peek();
        InstrukcjaZlozona widocznoscProcedur = zagniezdzenieWidocznosciProcedur.peek();
        zakres.dodajInstrukcje(new WywolanieProcedury(nazwa, argumenty, widocznoscProcedur));
        return this;
    }

    /**
     * dodaje instrukcje wywolania procedury bezargumentowej
     * @param nazwa
     * @return
     */
    public Builder wywolanieProcedury(String nazwa){
        InstrukcjaZlozona zakres = zagniezdzenieInstrukcji.peek();
        InstrukcjaZlozona widocznoscProcedur = zagniezdzenieWidocznosciProcedur.peek();
        zakres.dodajInstrukcje(new WywolanieProcedury(nazwa, widocznoscProcedur));
        return this;
    }

    /**
     *  obslugujacy wszystkie instrukcje warunkowe
     * @param warunek "<" , ">" , "==" , "<=" lub ">="
     * @param wyr1 wyrazenie z lewej
     * @param wyr2 wyrazenie z prawej
     */
    public IfBuilder rozpocznijInstrukcjeWarunkowa(String warunek, Wyrazenie wyr1, Wyrazenie wyr2){
        return new IfBuilder(this, warunek, wyr1, wyr2);
    }

    public PetlaBuilder rozpocznijPetle(char zmiennaSterujaca, Wyrazenie wyrazenieInicjalizujace){
        return new PetlaBuilder(this, zmiennaSterujaca, wyrazenieInicjalizujace);
    }

    public BlokBuilder rozpocznijBlok(){
        return new BlokBuilder(this);
    }
    //Ponizsze procedury musza byc nadpisane

    /**
     * zamyka zakres aktualnie rozpatrywanej instrukcji; Tak jak '}' w kodzie
     * @return Builder instrukcji nadrzednej
     */
    public Builder zamknijZakres(){
        throw new BladMacchiato("Blad budowy programu");
    }

    /**
     * Tworzy nowa procedure widoczna w bloku w ktorym ja zadeklarowano; mozna wywolac tylko na BlokBuilder
     * @param nazwa nazwa procedury
     * @param argumenty argumenty jakie przyjmuje porcedura
     * @return builder stworzonej procedury
     */
    public ProceduraBuilder rozpocznijProcedure(String nazwa, char[] argumenty){
        throw new BladMacchiato("Blad budowy programu");
    }
    /**
     * Tworzy nowa procedure (bez argumentow) widoczna w bloku w ktorym ja zadeklarowano; mozna wywolac tylko na
     * BlokBuilder
     * @param nazwa nazwa procedury
     * @return builder stworzonej procedury
     */
    public ProceduraBuilder rozpocznijProcedure(String nazwa){
        throw new BladMacchiato("Blad budowy programu");
    }

    /**
     * Deklaruje zmienna; mozliwe do wywolania tylko na BlokBuilder lub ProceduraBuilder
     * @param nazwa nazwa zmiennej
     * @param wyr wyrazenie do zainicjowania
     * @return builder instrukcji w ktorej zostala zadeklarowana zmienna
     */
    public Builder zadeklarujZmienna(char nazwa, Wyrazenie wyr){
        throw new BladMacchiato("Blad budowy programu");
    }

    /**
     * metoda tworzaca program
     * @return
     */
    public Program zbuduj(){
        throw new BladMacchiato("Blad budowy programu");
    }
    public InstrukcjaZlozona getInstrukcja(){
        throw new BladMacchiato("Blad budowy programu");
    }
}
