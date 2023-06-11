package Instrukcje;

import Wyjatki.PodwojnaDeklaracja;
import Wykonanie.Debugger;
import Wyrazenia.Wyrazenie;

import java.util.*;

public class Procedura extends InstrukcjaZWartosciowaniem{
    protected Set<Character> argumenty; // sluzy do upewnienia sie ze nazwy argumentow, w deklaracji procedury,
    // nie powtarzaja sie
    protected Set<Character> zadklarowaneZmienne; // odpowiada zmiennym z ciagu deklaracji w procedurze; nie zaliczaja
    //sie do nich zmienne o nazwach argumentow
    protected List<Deklaracja> wartosciArgumentow; //deklaracje zmiennych odpowiadajacych argumentom znajdujace sie na
    // poczatku listy instrukcji. z poczatku wyrazenie = null. Jest uzupelniane w momencie wywolania procedury

    /**
     * Procedura musi znajdowac sie w jakims bloku i wymaga informacji o nim
     * @param zakresZewnetrzny zakres widocznosci w ktorym zadeklarowana jest procedura
     * @param argumenty tablica char zawierajace podane w kolejnosci nazwy argumentow
     */
    public Procedura(InstrukcjaZWartosciowaniem zakresZewnetrzny, char argumenty[]){
        super(zakresZewnetrzny.wartWewnetrzne);
        this.argumenty = new LinkedHashSet<Character>(); //pragniemy zachowac porzadek dodawania elementow
        this.wartosciArgumentow = new ArrayList<Deklaracja>();
        zadklarowaneZmienne = new HashSet<Character>();

        // jak rowniez zagwarantowac to ze sie nie powtorza
        for (char arg : argumenty) {
            if(this.argumenty.contains(arg))
                throw new PodwojnaDeklaracja(arg);
            this.argumenty.add(arg);
            Deklaracja dekl = new Deklaracja(arg, null); //przy konstrukcji obiektu dajemy tu nulla,
            //podmieniamy na prawdziwe wyrazenie w momencie wywolania procedury
            wartosciArgumentow.add(dekl);
            dekl.wartNadrzedne = wartWewnetrzne;
            instrukcje.dodajInstrukcje(dekl);
            this.argumenty.add(arg);
        }

    }

    /**
     * konstruktor procedury bezparametrowej
     * @param zakresZewnetrzny  zakres widocznosci w ktorym zadeklarowana jest procedura
     */
    public Procedura(InstrukcjaZWartosciowaniem zakresZewnetrzny){
        super(zakresZewnetrzny.wartWewnetrzne);
        this.argumenty = new LinkedHashSet<Character>(); //pragniemy zachowac porzadek dodawania elementow
        this.wartosciArgumentow = new ArrayList<Deklaracja>();
        zadklarowaneZmienne = new HashSet<Character>();


    }

    /**
     * Przekazanie argumentow do procedury nastepuje w ten sposob, ze w momencie wywolania podajemy porcedurze
     * liste literalow, ktore sa wynikiem ewaluacji wyrazen w miejscu wywolania procedury.
     * @param wyrazenia Lista wyrazen podanych w kolejnosci wystepowania argumentow
     */
    public void ustawArgumenty(List<Wyrazenie> wyrazenia){
        Iterator<Wyrazenie> wyrazenieZWywolania = wyrazenia.iterator();
        Iterator<Deklaracja> deklaracjaArgumentu = wartosciArgumentow.iterator();
        while (deklaracjaArgumentu.hasNext()){
            Deklaracja argument = deklaracjaArgumentu.next();
            argument.setWyrazenie(wyrazenieZWywolania.next());
            argument.widocznoscProcedur = widocznoscProcedur;
        }
    }

    /**
     * nalezy pamietac by te metode wywolywac przed dodaniem jakichkolwiek instrukcji
     * @param zmienna
     * @param wartosc
     */
    public void dodajDeklaracje(char zmienna, Wyrazenie wartosc){
        if(zadklarowaneZmienne.contains(zmienna))
            throw new PodwojnaDeklaracja(zmienna);
        zadklarowaneZmienne.add(zmienna);
        Deklaracja dekl = new Deklaracja(zmienna, wartosc);
        dekl.wartNadrzedne = wartWewnetrzne;
        instrukcje.dodajInstrukcje(dekl);
    }

    /**
     * metoda przydatna do wypisywania naglowka porcedury. drukuje liste parametrow
     * @return jednoliterowe nazwy zmiennych bedace parametrami
     */
    public String getArgumenty(){
        return argumenty.toString();
    }

    public int getLiczbaPArametrow(){
        return argumenty.size();
    }

    @Override
    public InstrukcjaPojedyncza nastepnaInstrukcjaPojedyncza(Debugger debugger){
        return instrukcje.nastepnaInstrukcjaPojedyncza(debugger);
    }
    @Override
    public Instrukcja nastepnaInstrukcja(){
        return instrukcje.nastepnaInstrukcja();
    }
    @Override
    public void wykonaj(){
        instrukcje.wykonaj();
    }
    @Override
    public String toString(){
        if(argumenty != null)
            return getArgumenty() + '\n' + instrukcje.toString();
        else
            return instrukcje.toString();
    }
}
