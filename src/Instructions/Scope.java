package Instructions;

import Exceptions.MacchiatosError;
import Exceptions.UndeclaredVariable;

public class Scope {
    private final static int numberOfVariables = 26;
    private Scope parentScope;
    private int[] variable;
    private boolean[] declared;
    public Scope(){
        this.parentScope = null;
        variable = new int[numberOfVariables];
        declared = new boolean[numberOfVariables];
    }
    public Scope(Scope parentScope){
        this.parentScope = parentScope;
        variable = new int[numberOfVariables];
        declared = new boolean[numberOfVariables];
    }
    public Scope valuation(int depth){
        if(depth == 0 && parentScope != null){
            // we acquire the valuation from the higher block and override the variables
            Scope valuation = parentScope.valuation(0);
            for (int i = 0; i < variable.length; i++){
                if (declared[i]) {
                    valuation.variable[i] = variable[i];
                    valuation.declared[i] = declared[i];
                }
            }
            return valuation;
        }
        else if(depth == 0 && parentScope == null){ 
            Scope valuation = new Scope();
            for (int i = 0; i< variable.length; i++){
                valuation.variable[i] = this.variable[i];
                valuation.declared[i] = this.declared[i];
            }
            return valuation;
        }
        else if(depth > 0 && parentScope != null)
            return parentScope.valuation(depth - 1);
        return null;
    }

    // code that calls this method will have to complete the error with missing data about the instruction and the valuation    
    public void set(char variableName, int newValue) throws MacchiatosError {
        if (declared[variableName - 'a'])
            variable[variableName - 'a'] = newValue;
        else if(parentScope != null)
            parentScope.set(variableName, newValue);
        else
            throw new UndeclaredVariable();
        // we are not setting the valuation because we could get the parent scope
    }

    public int get(char variableName) throws MacchiatosError {
        if (declared[variableName - 'a'])
            return variable[variableName - 'a'];
        else if(parentScope != null)
            return parentScope.get(variableName);
        throw new UndeclaredVariable();
    }
    public void declare(char name, int value){
        declared[name - 'a'] = true;
        variable[name - 'a'] = value;
    }

    public String toString(){
        String result ="";
        for (int i = 0; i < variable.length; i++){
            if(declared[i]){
                result += (char)(i + 'a') + " = " + Integer.toString(variable[i]) + '\n';
            }
        }
        return result;
    }
}
