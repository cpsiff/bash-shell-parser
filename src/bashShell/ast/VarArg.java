package bashShell.ast;

public class VarArg extends SingleArg {
    private Terminal variable;

    public VarArg(Terminal variable){
        this.variable = variable;
    }

    /**
     * Print node type and visit children in order
     */
    public String visit(){
        return("VarArg" + this.variable.visit());
    }
}