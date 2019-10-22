package bashShell.ast;

public class NullCmd {
    private Command cmd = null;

    public NullCmd(){
        this.cmd = null;
    }

    /**
     * Print node type and visit children in order
     */
    public String visit(){
        return("NullCmd");
    }
}
