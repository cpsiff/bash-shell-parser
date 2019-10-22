package bashShell.ast;

public class FNameArg extends SingleArg  {
    private Terminal term;

    public FNameArg(Terminal term){
        this.term = term;
    }

    /**
     * Print node type and visit children in order
     */
    public String visit(){
        return("FNameArg" + this.term.visit());
    }
}