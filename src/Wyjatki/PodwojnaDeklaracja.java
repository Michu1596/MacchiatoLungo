package Wyjatki;

public class PodwojnaDeklaracja extends BladMacchiato{
    private char zmienna;
    public PodwojnaDeklaracja(char zmienna){
        this.zmienna = zmienna;
    }
    public String toString(){
        return "Podwojna deklaracaja zmiennej " + zmienna;
    }
}
