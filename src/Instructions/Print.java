package Instructions;

import Wyrazenia.Expression;

public class Print extends SingleInstruction {
    private Expression exp1;
    public Print(Expression exp1){
        super();
        this.exp1 = exp1;
    }
    @Override
    public void execute(){
        System.out.println(exp1.evaluate(parentScope));
    }
    @Override
    public String toString(){
        return "PRINT( " + exp1.toString() + " )" + '\n';
    }
}
