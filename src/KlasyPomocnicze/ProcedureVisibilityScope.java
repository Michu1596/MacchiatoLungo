package KlasyPomocnicze;

import Instructions.Procedure;
import Wyjatki.NiezadeklarowanaProcedura;
import Wyjatki.PodwojnaDeklaracjaProcedury;

import java.util.HashMap;
import java.util.Map;

public class ProcedureVisibilityScope {
    protected ProcedureVisibilityScope nadrzedny;
    protected Map<String, Procedure> procedury;

    public ProcedureVisibilityScope(){
        procedury = new HashMap<String, Procedure>();
    }
    public ProcedureVisibilityScope(ProcedureVisibilityScope nadrzedny){
        procedury = new HashMap<String, Procedure>();
        this.nadrzedny = nadrzedny;
    }

    public void declareProcedure(String nazwaProcedury, Procedure procedure){
        if (procedury.containsKey(nazwaProcedury))
            throw new PodwojnaDeklaracjaProcedury(nazwaProcedury);
        else {
            procedury.put(nazwaProcedury, procedure);
        }
    }

    /**
     * zwraca procedure o podanej nazwie; gdy takiej nie ma to rzuca wyjatek
     * @param nazwaProcedury
     * @return
     */
    public Procedure get(String nazwaProcedury){
        if (procedury.containsKey(nazwaProcedury))
            return procedury.get(nazwaProcedury);
        else if(nadrzedny != null)
            return nadrzedny.get(nazwaProcedury);
        throw new NiezadeklarowanaProcedura();
    }

    public boolean czyZawiera(String nazwaProcedury){
        return procedury.containsKey(nazwaProcedury);
    }

    public String toString(){
        String wynik ="";
        for (Map.Entry<String, Procedure> para : procedury.entrySet())
            wynik += para.getKey() + "( " + para.getValue().getArgs() + " )" + '\n';

        return wynik;
    }
}
