package Exceptions;

public class DoubleDeclaration extends MacchiatosError {
    private char variable;
    public DoubleDeclaration(char variable){
        this.variable = variable;
    }
    public String toString(){
        return "Double declaration error " + variable;
    }
}
