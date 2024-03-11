package Factories;

import Wyrazenia.Variable;

public class VariableFactory {
    public static Variable nazwa(char name){
        return new Variable(name);
    }
}
