package Wyjatki;

public class DoubleDeclaration extends BladMacchiato{
    private char zmienna;
    public DoubleDeclaration(char zmienna){
        this.zmienna = zmienna;
    }
    public String toString(){
        return "Podwojna deklaracaja zmiennej " + zmienna;
    }
}
