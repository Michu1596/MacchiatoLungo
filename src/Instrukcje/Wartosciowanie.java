package Instrukcje;

import Wyjatki.BladMacchiato;
import Wyjatki.NiezadeklarowanaZmienna;

public class Wartosciowanie {
    private Wartosciowanie nadrzedne;
    private int[] zmienne;
    private boolean[] zadeklarowane;
    public Wartosciowanie(){
        this.nadrzedne = null;
        zmienne = new int[26];
        zadeklarowane = new boolean[26];
    }
    public Wartosciowanie(Wartosciowanie nadrzedne){
        this.nadrzedne = nadrzedne;
        zmienne = new int[26];
        zadeklarowane = new boolean[26];
    }
    public Wartosciowanie wartosciowanie(int glebokosc){
        if(glebokosc == 0 && nadrzedne != null){
            //pozyskujemy wartosciowanie z wyzszego bloku i przyslaniamy nadpisane zmienne
            Wartosciowanie wart = nadrzedne.wartosciowanie(0);
            for (int i = 0; i < zmienne.length; i++){
                if (zadeklarowane[i]) {
                    wart.zmienne[i] = zmienne[i];
                    wart.zadeklarowane[i] = zadeklarowane[i];
                }
            }
            return wart;
        }
        else if(glebokosc == 0 && nadrzedne == null){ //najwyzszy blok
            //kopiujemy dwie tablice i zwracamy
            Wartosciowanie wart = new Wartosciowanie();
            for (int i=0; i<zmienne.length; i++){
                wart.zmienne[i] = this.zmienne[i];
                wart.zadeklarowane[i] = this.zadeklarowane[i];
            }
            return wart;
        }
        else if(glebokosc > 0 && nadrzedne != null)
            return nadrzedne.wartosciowanie(glebokosc - 1);
        return null;
    }

    //kod wywalujacy ta metode bedzie musial uzupelnic blad o brakujace dane o instrukcji i wartosciowaniu
    public void set(char nazwaZmiennej, int nowaWartosc) throws BladMacchiato{
        if (zadeklarowane[nazwaZmiennej - 'a'])
            zmienne[nazwaZmiennej - 'a'] = nowaWartosc;
        else if(nadrzedne != null)
            nadrzedne.set(nazwaZmiennej, nowaWartosc);
        else
            throw new NiezadeklarowanaZmienna(); //nie ustawiamy wartosciowania bo moglibysmy dostac to nadrzedne
    }

    public int get(char nazwaZmiennej) throws BladMacchiato{
        if (zadeklarowane[nazwaZmiennej - 'a'])
            return zmienne[nazwaZmiennej - 'a'];
        else if(nadrzedne != null)
            return nadrzedne.get(nazwaZmiennej);
        throw new NiezadeklarowanaZmienna();
    }
    public void deklaruj(char nazwa, int wartosc){
        zadeklarowane[nazwa - 'a'] = true;
        zmienne[nazwa - 'a'] = wartosc;
    }

    public String toString(){
        String wynik ="";
        for (int i = 0; i < zmienne.length; i++){
            if(zadeklarowane[i]){
                wynik += (char)(i + 'a') + " = " + Integer.toString(zmienne[i]) + '\n';
            }
        }
        return wynik;
    }
}
