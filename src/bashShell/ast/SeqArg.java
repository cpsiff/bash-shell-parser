package bashShell.ast;

public class SeqArg extends Argument {
    private Argument arg1;
    private Argument arg2;

    public SeqArg(Argument arg1, Argument arg2){
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    /**
     * Return node type and visit children in order
     * Add indentation and indent children one level more
     * @param i the indentation level (level of the tree, with 0 being the root
     * @return an indented, multi-line string describing the tree of this node and below
     */
    public String visit(int i){
        return(util.s(i) + "SeqArg\n" + arg1.visit(i+1) + arg2.visit(i+1));
    }
}
