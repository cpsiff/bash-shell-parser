package bashShell.ast;

public class Script extends AST {
    private Command c;

    public Script(Command c){
        this.c = c;
    }

    /**
     * Return node type and visit children in order
     * Add indentation and indent children one level more
     * @param i the indentation level (level of the tree, with 0 being the root
     * @return an indented, multi-line string describing the tree of this node and below
     */
    public String visit(int i){
        return(Util.s(i) + "Script\n" + this.c.visit(i+1));
    }
}