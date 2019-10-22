package bashShell.ast;

public class LiteralArg extends SingleArg {
    private Terminal literal;

    public LiteralArg(Terminal literal){
        this.literal = literal;
    }

    /**
     * Print node type and visit children in order
     */
    public void visit(){
        System.out.print("LiteralArg");
        this.literal.visit();
    }
}