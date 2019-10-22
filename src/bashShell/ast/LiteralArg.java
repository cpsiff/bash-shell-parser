package bashShell.ast;

public class LiteralArg extends SingleArg {
    private Terminal literal;

    public LiteralArg(Terminal literal){
        this.literal = literal;
    }

    /**
     * Print node type and visit children in order
     */
    public String visit(){
        return("LiteralArg" + this.literal.visit());
    }
}