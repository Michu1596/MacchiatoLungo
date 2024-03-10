package Buildery;

import Instrukcje.*;
import Wyjatki.BladMacchiato;
import Wykonanie.Program;
import Wyrazenia.Expresion;

import java.util.List;
import java.util.Stack;

/**
 *  Klasa Builder zawiera wszystkie metody potrzbne do utworzenia programu. Implementacji metod dostarczaja odpowiednie
 *  podklasy. Wywolanie zlej metody jest mozliwe ale skutkuje rzuceniem wyjatku. Czesc metod jest jest wspolna dla
 *  wszystkich builderow, stad zostaly zaimplementowane tutaj
 */
public abstract class Builder {
    protected Stack<complexInstruction> instructionNesting; //reprezentuje hierarchie zagniezdzenia instrukcji
    protected Stack<InstrukcjaZWartosciowaniem> scopesNesting; //reprezentuje hierarchie zagniezdzenia
    protected Stack<Block> proceduresVisibilityNesting;
    protected Builder parent; //pole uzupelniane w momencie dodawania instrukcji zlozonej do jakiegos zakrsu
    // widocznosci; wartosc ta jest zwracana w momencie zamkniecia zakresu widocznosci.

    public Builder(){
        instructionNesting = new Stack<>();
        scopesNesting = new Stack<>();
        proceduresVisibilityNesting = new Stack<>();
        parent = null;
    }

    /**
     * konstruktor tworzacy shallow copy buildera instrukcji nadrzednej. Pozwala on otrzymac dostep do hierarchii
     * zagniezdzen instrukcji, wartosciowan oraz widocznosci procedur. Uzywamy go wywolujac metody zaczynajace sie od
     * "rozpocznij"
     * @param program instrukcja nadrzedna. W jej zakresie znajduje sie aktualnie rozpatrywna instrukcja
     */
    public Builder(Builder program){
        instructionNesting = program.instructionNesting;
        scopesNesting = program.scopesNesting;
        proceduresVisibilityNesting = program.proceduresVisibilityNesting;
        parent = program;
    }

    /**
     * dodaje instrukcje Print do aktualnej instrukcji
     * @param wyr wyrazenie do wydrukowania
     * @return
     */
    public Builder print(Expresion wyr){
        complexInstruction zakres = instructionNesting.peek();
        zakres.addIntruction(new Print(wyr));
        return this;
    }

    /**
     * dodaje instrukcje przypisania do aktualnej instrukcji
     * @param zmienna zmienna do ktorej przypisujemy
     * @param wyr2 wyrazenie jakiego wartosc przypisujemy
     * @return
     */

    public Builder przypisanie(char zmienna, Expresion wyr2){
        complexInstruction zakres = instructionNesting.peek();
        zakres.addIntruction(new Przypisanie(zmienna, wyr2));
        return this;
    }

    /**
     * dodaje instrukcje wywolania procedury
     * @param nazwa nazwa procedury do wywolania
     * @param argumenty lista wyrazen zawierajaca argumenty
     * @return
     */
    public Builder wywolanieProcedury(String nazwa, List<Expresion> argumenty){
        complexInstruction zakres = instructionNesting.peek();
        complexInstruction widocznoscProcedur = proceduresVisibilityNesting.peek();
        zakres.addIntruction(new WywolanieProcedury(nazwa, argumenty, widocznoscProcedur));
        return this;
    }

    /**
     * dodaje instrukcje wywolania procedury bezargumentowej
     * @param nazwa
     * @return
     */
    public Builder wywolanieProcedury(String nazwa){
        complexInstruction zakres = instructionNesting.peek();
        complexInstruction widocznoscProcedur = proceduresVisibilityNesting.peek();
        zakres.addIntruction(new WywolanieProcedury(nazwa, widocznoscProcedur));
        return this;
    }

    /**
     *  obslugujacy wszystkie instrukcje warunkowe
     * @param warunek "<" , ">" , "==" , "<=" lub ">="
     * @param wyr1 wyrazenie z lewej
     * @param wyr2 wyrazenie z prawej
     */
    public IfBuilder rozpocznijInstrukcjeWarunkowa(String warunek, Expresion wyr1, Expresion wyr2){
        return new IfBuilder(this, warunek, wyr1, wyr2);
    }

    public PetlaBuilder rozpocznijPetle(char zmiennaSterujaca, Expresion expresionInicjalizujace){
        return new PetlaBuilder(this, zmiennaSterujaca, expresionInicjalizujace);
    }

    public BlockBuilder beginBlock(){
        return new BlockBuilder(this);
    }


    //Ponizsze procedury musza byc nadpisane

    /**
     * zamyka zakres aktualnie rozpatrywanej instrukcji; Tak jak '}' w kodzie
     * @return Builder instrukcji nadrzednej
     */
    public Builder finishScope(){
        throw new BladMacchiato("Blad budowy programu");
    }

    /**
     * Tworzy nowa procedure widoczna w bloku w ktorym ja zadeklarowano; mozna wywolac tylko na BlokBuilder
     * @param nazwa nazwa procedury
     * @param argumenty argumenty jakie przyjmuje porcedura
     * @return builder stworzonej procedury
     */
    public ProcedureBuilder beginProcedure(String nazwa, char[] argumenty){
        throw new BladMacchiato("Rozpoczecie procedury mozliwe jest jedynie w bloku");
    }
    /**
     * Tworzy nowa procedure (bez argumentow) widoczna w bloku w ktorym ja zadeklarowano; mozna wywolac tylko na
     * BlokBuilder
     * @param nazwa nazwa procedury
     * @return builder stworzonej procedury
     */
    public ProcedureBuilder beginProcedure(String nazwa){
        throw new BladMacchiato("Rozpoczecie procedury mozliwe jest jedynie w bloku");
    }

    /**
     * Deklaruje zmienna; mozliwe do wywolania tylko na BlokBuilder lub ProceduraBuilder
     * @param nazwa nazwa zmiennej
     * @param wyr wyrazenie do zainicjowania
     * @return builder instrukcji w ktorej zostala zadeklarowana zmienna
     */
    public Builder declareVariable(char nazwa, Expresion wyr){
        throw new BladMacchiato("Deklaracja zmiennej moze nastapic jedynie w bloku lub procedurze");
    }

    /**
     * metoda tworzaca program
     * @return gotowy program
     */
    public Program zbuduj(){
        throw new BladMacchiato("Zbudownia programu mozliwe jest jedynie z poziomu ProgramBuilder'a");
    }
    public complexInstruction getInstruction(){
        throw new BladMacchiato("Blad budowy programu");
    }
}
