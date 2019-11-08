package bashShell.ast;

import bashShell.DisplayTreeVisitor;

public class Script extends AST {
    private Command c;

    public Script(Command c){
        this.c = c;
    }

    public Command getC() {
        return c;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitScript(this, i);
    }
}