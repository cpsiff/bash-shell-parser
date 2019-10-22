package bashShell.ast;

public class Terminal extends AST {
    private String spelling;

    public Terminal(String spelling){
        this.spelling = spelling;
    }

    /**
     * Return node type and visit children in order
     * Add indentation and indent children one level more
     * @param i the indentation level (level of the tree, with 0 being the root
     * @return an indented, multi-line string describing the tree of this node and below
     */
    public String visit(int i){
        return(" (" + this.spelling + ")\n");
    }
}