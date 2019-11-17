package bashShell.ast;

import bashShell.DecorateASTVisitor;
import bashShell.DisplayTreeVisitor;
import bashShell.Type;

public class NullArg extends Argument {
    private Argument arg = null;
    public Type type;

    public NullArg(){
        this.arg = null;
        this.type = Type.nulltype;
    }

    public Argument getArg() {
        return arg;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitNullArg(this, i);
    }
    public Object accept(DecorateASTVisitor visitor, Object o){return visitor.visitNullArg(this, o);}
}