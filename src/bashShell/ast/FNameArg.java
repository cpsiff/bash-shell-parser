package bashShell.ast;

public class FNameArg extends SingleArg  {
    private Terminal term;

    public FNameArg(Terminal term){
        this.term = term;
    }

    /**
     * Print node type and visit children in order
     */
    public void visit(){
        System.out.print("FNameArg");
        this.term.visit();
    }
}