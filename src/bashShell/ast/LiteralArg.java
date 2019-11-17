package bashShell.ast;

import bashShell.DecorateASTVisitor;
import bashShell.DisplayTreeVisitor;
import bashShell.Type;

public class LiteralArg extends SingleArg {
    private Terminal literal;
    public Type type;

    public LiteralArg(Terminal literal){
        this.literal = literal;
    }

    public Terminal getTerm() {
        return literal;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitLiteralArg(this, i);
    }
    public Object accept(DecorateASTVisitor visitor, Object o){return visitor.visitLiteralArg(this, o);}
}