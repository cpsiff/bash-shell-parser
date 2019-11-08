package bashShell.ast;

import bashShell.DisplayTreeVisitor;

public class ForCommand extends Command {
    private VarArg var;
    private Argument args;
    private Command doBody;

    public ForCommand(VarArg var, Argument args, Command doBody){
        this.var = var;
        this.args = args;
        this.doBody = doBody;
    }

    public Command getDoBody() {
        return doBody;
    }

    public Argument getArgs() {
        return args;
    }

    public VarArg getVar() {
        return var;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitForCommand(this, i);
    }
}