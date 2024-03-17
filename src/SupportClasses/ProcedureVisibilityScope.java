package SupportClasses;

import Instructions.Procedure;
import Exceptions.UndeclaredProcedure;
import Exceptions.procedureDoubleDeclaration;

import java.util.HashMap;
import java.util.Map;

public class ProcedureVisibilityScope {
    protected ProcedureVisibilityScope parentVisibilityScope;
    protected Map<String, Procedure> procedures;

    public ProcedureVisibilityScope(){
        procedures = new HashMap<String, Procedure>();
    }
    public ProcedureVisibilityScope(ProcedureVisibilityScope parentVisibilityScope){
        procedures = new HashMap<String, Procedure>();
        this.parentVisibilityScope = parentVisibilityScope;
    }

    public void declareProcedure(String procedureName, Procedure procedure){
        if (procedures.containsKey(procedureName))
            throw new procedureDoubleDeclaration(procedureName);
        else {
            procedures.put(procedureName, procedure);
        }
    }

    /**
     * returns procedure of the given name; if there is no such procedure, it throws an exception
     * @param procedureName
     * @return
     */
    public Procedure get(String procedureName){
        if (procedures.containsKey(procedureName))
            return procedures.get(procedureName);
        else if(parentVisibilityScope != null)
            return parentVisibilityScope.get(procedureName);
        throw new UndeclaredProcedure();
    }

    public boolean ifContains(String procedureName){
        return procedures.containsKey(procedureName);
    }

    public String toString(){
        String result ="";
        for (Map.Entry<String, Procedure> pair : procedures.entrySet())
            result += pair.getKey() + "( " + pair.getValue().getArgs() + " )" + '\n';

        return result;
    }
}
