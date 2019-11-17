package bashShell.ast;

import bashShell.DecorateASTVisitor;
import bashShell.DisplayTreeVisitor;

public class SeqCmd extends Command {
    private Command c1;
    private Command c2;

    public SeqCmd(Command c1, Command c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    public Command getC2() {
        return c2;
    }

    public Command getC1() {
        return c1;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitSeqCmd(this, i);
    }
    public Object accept(DecorateASTVisitor visitor, Object o){return visitor.visitSeqCmd(this, o);}
}