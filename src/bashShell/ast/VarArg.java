package bashShell.ast;

public class VarArg extends SingleArg {
    private Terminal variable;

    public VarArg(Terminal variable){
        this.variable = variable;
    }

    /**
     * Return node type and visit children in order
     * Add indentation and indent children one level more
     * @param i the indentation level (level of the tree, with 0 being the root
     * @return an indented, multi-line string describing the tree of this node and below
     */
    public String visit(int i){
        return(util.s(i) + "VarArg" + this.variable.visit(i+1));
    }
}