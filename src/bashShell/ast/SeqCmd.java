package bashShell.ast;

public class SeqCmd extends Command {
    private Command c1;
    private Command c2;

    public SeqCmd(Command c1, Command c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    /**
     * Return node type and visit children in order
     * Add indentation and indent children one level more
     * @param i the indentation level (level of the tree, with 0 being the root
     * @return an indented, multi-line string describing the tree of this node and below
     */
    public String visit(int i){
        return(util.s(i) + "SeqCmd\n" + this.c1.visit(i+1) + this.c2.visit(i+1));
    }
}