package bashShell.ast;

public class IfCmd extends Command{
    private FNameArg command;
    private Argument args;
    private Command thenBlock;
    private Command elseBlock;

    public IfCmd(FNameArg command, Argument args, Command thenBlock, Command elseBlock){
        this.command = command;
        this.args = args;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    /**
     * Print node type and visit children in order
     */
    public String visit(){
        return("IfCmd\n" + this.command.visit() + this.args.visit() + this.thenBlock.visit() + this.elseBlock.visit());
    }
}