package Wykonanie;
import java.util.*;

import Instrukcje.Instrukcja;
import Instrukcje.InstrukcjaPojedyncza;
import Instrukcje.Wartosciowanie;

public class Program {
    protected Instrukcja program;
    protected InstrukcjaPojedyncza nastepnaPojedyncza;
    public Program(Instrukcja program){
        this.program = program;
    }

    public void wykonanieBezDebugowania(){
        program.wykonaj();
    }

    protected void cont(Instrukcja nast, Debugger debugger){
        if(nast == null){
            System.out.println("Program skończył się");
            return;
        }
        while (nast != null){
            nast.wykonaj();
            nast = program.nastepnaInstrukcjaPojedyncza(debugger);
        }

    }
    protected boolean step(int ile, Debugger debugger){
        int i = 0;
        while (i < ile && nastepnaPojedyncza != null){
            nastepnaPojedyncza.wykonaj();
            nastepnaPojedyncza = program.nastepnaInstrukcjaPojedyncza(debugger);
            i++;
        }
        if(nastepnaPojedyncza == null){
            System.out.println("Program zakonczyl sie przed wykonaniem zadanej liczby instrukcji");
            return false;
        }
        System.out.println(debugger.getKtoraNastepna().toString());
        System.out.println("Nastepna instrukcja: \n");
        return true;
    }
    public void wykonajZDebugowaniem(){
        Debugger debug = new Debugger();

        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
       // System.out.print("Enter a string: ");
        //String komenda = sc.nextLine();

        nastepnaPojedyncza = program.nastepnaInstrukcjaPojedyncza(debug);

        boolean petla = true;
        while (petla) {
            System.out.print("Podaj komende: ");
            String komenda = sc.nextLine();
            switch (komenda.charAt(0)) {
                case 'c': {
                    cont(nastepnaPojedyncza, debug);
                    petla = false;
                    break;
                }
                case 'e': {
                    System.out.println("Koniec debugowania");
                    petla = false;
                    break;
                }
                case 'd': {
                    komenda = komenda.substring(1);
                    komenda = komenda.trim();
                    Wartosciowanie doWyswitlenia = nastepnaPojedyncza.display(Integer.parseInt(komenda));
                    if(doWyswitlenia == null){
                        System.out.println("Za duzy parametr dla komendy d");
                    }
                    else
                        System.out.println(doWyswitlenia.toString());
                    break;
                }
                case 's': {
                    komenda = komenda.substring(1);
                    komenda = komenda.trim();
                    System.out.println("Liczba obliczona: ");
                    System.out.println(Integer.parseInt(komenda));
                    petla = step(Integer.parseInt(komenda), debug);
                    break;
                }
            }
        }
    }
}
