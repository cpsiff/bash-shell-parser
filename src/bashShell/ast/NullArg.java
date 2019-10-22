package bashShell.ast;

public class NullArg extends Argument {
    private Argument arg = null;

    public NullArg(){
        this.arg = null;
    }

    /**
     * Print node type and visit children in order
     */
    public String visit(){
        return("NullArg");
    }
}