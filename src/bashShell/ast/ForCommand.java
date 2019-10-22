package bashShell.ast;

public class ForCommand extends Command {
    private VarArg var;
    private Argument args;
    private Command doBody;

    public ForCommand(VarArg var, Argument args, Command doBody){
        this.var = var;
        this.args = args;
        this.doBody = doBody;
    }

    /**
     * Print node type and visit children in order
     */
    public String visit() {
        return("ForCommand\n" + this.var.visit() + this.args.visit() + this.doBody.visit());
    }
}