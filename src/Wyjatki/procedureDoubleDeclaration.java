package Wyjatki;

public class procedureDoubleDeclaration extends MacchiatosError {
    public String nazwa;
    public procedureDoubleDeclaration(String nazwa){
        this.nazwa = nazwa;
    }
    public String toString(){
        return "Podwojna deklaracaja procedury " + nazwa;
    }
}
