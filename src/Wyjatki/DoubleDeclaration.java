package Wyjatki;

public class DoubleDeclaration extends MacchiatosError {
    private char zmienna;
    public DoubleDeclaration(char zmienna){
        this.zmienna = zmienna;
    }
    public String toString(){
        return "Podwojna deklaracaja zmiennej " + zmienna;
    }
}
