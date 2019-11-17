package bashShell.ast;

import bashShell.DecorateASTVisitor;
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
    public Object accept(DecorateASTVisitor visitor, Object o){return visitor.visitScript(this, o);}
}