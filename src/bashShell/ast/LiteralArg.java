package bashShell.ast;

public class LiteralArg extends SingleArg {
    private Terminal literal;

    public LiteralArg(Terminal literal){
        this.literal = literal;
    }

    /**
     * Return node type and visit children in order
     * Add indentation and indent children one level more
     * @param i the indentation level (level of the tree, with 0 being the root
     * @return an indented, multi-line string describing the tree of this node and below
     */
    public String visit(int i){
        return(util.s(i) + "LiteralArg" + this.literal.visit(i+1));
    }
}