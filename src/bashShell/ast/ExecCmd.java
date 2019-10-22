package bashShell.ast;

public class ExecCmd extends Command {
    private FNameArg command;
    private Argument args;

    public ExecCmd(FNameArg command, Argument args){
        this.command = command;
        this.args = args;
    }
    /**
     * Print node type and visit children in order
     */
    public void visit(){
        System.out.println("ExecCmd");
        this.command.visit();
        this.args.visit();
    }
}