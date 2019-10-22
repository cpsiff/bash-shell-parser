package bashShell.ast;

public class Terminal extends AST {
    private String spelling;

    public Terminal(String spelling){
        this.spelling = spelling;
    }

    /**
     * Print node type and visit children in order
     */
    public String visit(){
        return(" (" + this.spelling + ")\n");
    }
}