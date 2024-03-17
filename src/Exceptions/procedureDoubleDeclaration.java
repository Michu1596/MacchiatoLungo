package Exceptions;

public class procedureDoubleDeclaration extends MacchiatosError {
    public String name;
    public procedureDoubleDeclaration(String name){
        this.name = name;
    }
    public String toString(){
        return "Procedure double declaration " + name;
    }
}
