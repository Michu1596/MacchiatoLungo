package KlasyPomocnicze;

import Instrukcje.Procedura;
import Wyjatki.BladMacchiato;
import Wyjatki.NiezadeklarowanaProcedura;
import Wyjatki.NiezadeklarowanaZmienna;

import java.util.HashMap;
import java.util.Map;

public class ZakresWidocznosciProcedur {
    protected ZakresWidocznosciProcedur nadrzedny;
    protected Map<String, Procedura> procedury;

    public ZakresWidocznosciProcedur(){
        procedury = new HashMap<String, Procedura>();
    }
    public ZakresWidocznosciProcedur(ZakresWidocznosciProcedur nadrzedny){
        procedury = new HashMap<String, Procedura>();
        this.nadrzedny = nadrzedny;
    }

    public void deklarujProcedure(String nazwaProcedury, Procedura procedura){
        if (procedury.containsKey(nazwaProcedury)) //TODO zrobic wyjatek o podwojnej dekl. procedury
            throw new BladMacchiato("Podwojna deklaracja procedury w tym samym zakresie widocznosci");
        else {
            procedury.put(nazwaProcedury, procedura);
        }
    }

    /**
     * zwraca procedure o podanej nazwie; gdy takiej nie ma to rzuca wyjatek
     * @param nazwaProcedury
     * @return
     */
    public Procedura get(String nazwaProcedury){
        if (procedury.containsKey(nazwaProcedury))
            return procedury.get(nazwaProcedury);
        else if(nadrzedny != null)
            return nadrzedny.get(nazwaProcedury);
        throw new NiezadeklarowanaProcedura();
        //TODO przetestowac pod katem wywolania niezadeklarownej procedury
    }

    public boolean czyZawiera(String nazwaProcedury){
        return procedury.containsKey(nazwaProcedury);
    }

    public String toString(){
        String wynik ="";
        for (Map.Entry<String, Procedura> para : procedury.entrySet())
            wynik += para.getKey() + "( " + para.getValue().getArgumenty() + " )" + '\n';

        return wynik;
    }
}
