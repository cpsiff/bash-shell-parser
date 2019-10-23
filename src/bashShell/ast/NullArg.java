package bashShell.ast;

public class NullArg extends Argument {
    private Argument arg = null;

    public NullArg(){
        this.arg = null;
    }

    /**
     * Return node type and visit children in order
     * Add indentation and indent children one level more
     * @param i the indentation level (level of the tree, with 0 being the root
     * @return an indented, multi-line string describing the tree of this node and below
     */
    public String visit(int i){
        return(Util.s(i) + "NullArg\n");
    }
}