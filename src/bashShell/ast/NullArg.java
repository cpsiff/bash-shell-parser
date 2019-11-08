package bashShell.ast;

import bashShell.DisplayTreeVisitor;

public class NullArg extends Argument {
    private Argument arg = null;

    public NullArg(){
        this.arg = null;
    }

    public Argument getArg() {
        return arg;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitNullArg(this, i);
    }
}