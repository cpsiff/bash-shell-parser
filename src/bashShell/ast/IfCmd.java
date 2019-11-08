package bashShell.ast;

import bashShell.DisplayTreeVisitor;

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

    public Command getElseBlock() {
        return elseBlock;
    }

    public Command getThenBlock() {
        return thenBlock;
    }

    public Argument getArgs() {
        return args;
    }

    public FNameArg getCommand() {
        return command;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitIfCmd(this, i);
    }
}