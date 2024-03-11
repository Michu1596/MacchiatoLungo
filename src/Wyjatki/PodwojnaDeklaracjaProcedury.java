package Wyjatki;

public class PodwojnaDeklaracjaProcedury extends MacchiatosError {
    public String nazwa;
    public PodwojnaDeklaracjaProcedury(String nazwa){
        this.nazwa = nazwa;
    }
    public String toString(){
        return "Podwojna deklaracaja procedury " + nazwa;
    }
}
