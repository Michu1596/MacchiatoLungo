package Wykonanie;
import java.io.PrintWriter;
import java.util.*;

import Instrukcje.Instrukcja;
import Instrukcje.InstrukcjaPojedyncza;
import Instrukcje.Wartosciowanie;
import KlasyPomocnicze.ZakresWidocznosciProcedur;
import Wyjatki.BladMacchiato;

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
        Debugger poprzedniDebugger = new Debugger();
        while (i < ile && nastepnaPojedyncza != null){
            try {
                nastepnaPojedyncza.wykonaj();
                // to jest po to zeby nie podawal zbyt odleglej instrukcji
                poprzedniDebugger.setKtoraNastepna(debugger.getKtoraNastepna());
                nastepnaPojedyncza = program.nastepnaInstrukcjaPojedyncza(debugger);
                i++;
            }
            catch (BladMacchiato e){
                System.out.println("Napotkano wyjatek");
                System.out.println(e.toString());
                nastepnaPojedyncza = null; // konczymy wykonywanie programu
            }
        }
        if(nastepnaPojedyncza == null){
            System.out.println("Program zakonczyl sie przed wykonaniem zadanej liczby instrukcji");
            return false;
        }
        System.out.println("Nastepna instrukcja: \n");
        System.out.println(poprzedniDebugger.getKtoraNastepna().toString());
        return true;
    }
    public void wykonajZDebugowaniem(){
        Debugger debug = new Debugger();

        Scanner sc= new Scanner(System.in);

        System.out.println("Nast Poj");
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
                    if (Integer.parseInt(komenda) < 1)
                        System.out.println("Liczba krokow musi byc dodatnia");
                    else
                        petla = step(Integer.parseInt(komenda), debug);
                    break;
                }
                case 'm': {
                    String nazwaPlikuWy = komenda.substring(1);
                    nazwaPlikuWy = nazwaPlikuWy.trim();
                    try (PrintWriter plikWy = new PrintWriter(nazwaPlikuWy, "UTF-8")
                    )
                    {
                        Wartosciowanie doWyswietlenia = nastepnaPojedyncza.display(0);
                        ZakresWidocznosciProcedur procedury = nastepnaPojedyncza.getWidocznoscProcedur();
                        plikWy.println(doWyswietlenia.toString() + '\n' + procedury.toString());

                    }catch (Exception e) {
                        System.out.println("Nie mozna utworzyc pliku wyjsciowego");
                        System.out.println(e.getClass());
                        System.out.println(e);
                        throw new RuntimeException();
                    }
                }
            }
        }
    }
}
