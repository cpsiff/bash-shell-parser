package bashShell.ast;

import bashShell.DecorateASTVisitor;
import bashShell.DisplayTreeVisitor;

public class NullCmd extends Command {
    private Command cmd = null;

    public NullCmd(){
        this.cmd = null;
    }

    public Command getCmd() {
        return cmd;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitNullCmd(this, i);
    }
    public Object accept(DecorateASTVisitor visitor, Object o){return visitor.visitNullCmd(this, o);}
}
