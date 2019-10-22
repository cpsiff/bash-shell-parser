package bashShell.ast;

public class AssignCmd extends Command {
    private VarArg lValue;
    private SingleArg rValue;

    public AssignCmd(VarArg lValue, SingleArg rValue){
        this.lValue = lValue;
        this.rValue = rValue;
    }

    /**
     * Print node type and visit children in order
     */
    public String visit(){
        return("AssignCmd\n" + this.lValue.visit() + this.rValue.visit());
    }
}