package bashShell.ast;

public class SeqCmd extends Command {
    private Command c1;
    private Command c2;

    public SeqCmd(Command c1, Command c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    /**
     * Print node type and visit children in order
     */
    public String visit(){
        return("SeqCmd\n" + this.c1.visit() + this.c2.visit());
    }
}