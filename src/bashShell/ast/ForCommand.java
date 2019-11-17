package bashShell.ast;

import bashShell.DecorateASTVisitor;
import bashShell.DisplayTreeVisitor;

public class ForCommand extends Command {
    private VarArg var;
    private Argument arg;
    private Command doBody;

    public ForCommand(VarArg var, Argument arg, Command doBody){
        this.var = var;
        this.arg = arg;
        this.doBody = doBody;
    }

    public Command getDoBody() {
        return doBody;
    }

    public Argument getArg() {
        return arg;
    }

    public VarArg getVar() {
        return var;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitForCommand(this, i);
    }
    public Object accept(DecorateASTVisitor visitor, Object o){return visitor.visitForCommand(this, o);}
}