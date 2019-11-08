package bashShell.ast;

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
}
