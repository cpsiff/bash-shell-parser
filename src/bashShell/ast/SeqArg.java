package bashShell.ast;

import bashShell.DecorateASTVisitor;
import bashShell.DisplayTreeVisitor;

public class SeqArg extends Argument {
    private Argument arg1;
    private Argument arg2;

    public SeqArg(Argument arg1, Argument arg2){
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public Argument getArg2() {
        return arg2;
    }

    public Argument getArg1() {
        return arg1;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitSeqArg(this, i);
    }
    public Object accept(DecorateASTVisitor visitor, Object o){return visitor.visitSeqArg(this, o);}
}
