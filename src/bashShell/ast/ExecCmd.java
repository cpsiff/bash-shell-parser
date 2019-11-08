package bashShell.ast;

import bashShell.DisplayTreeVisitor;

public class ExecCmd extends Command {
    private FNameArg command;
    private Argument args;

    public ExecCmd(FNameArg command, Argument args){
        this.command = command;
        this.args = args;
    }

    public Argument getArgs() {
        return args;
    }

    public FNameArg getCommand() {
        return command;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitExecCmd(this, i);
    }
}