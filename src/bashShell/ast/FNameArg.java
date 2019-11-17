package bashShell.ast;

import bashShell.DecorateASTVisitor;
import bashShell.DisplayTreeVisitor;
import bashShell.Type;

public class FNameArg extends SingleArg  {
    private Terminal term;
    public Type type;

    public FNameArg(Terminal term){
        this.term = term;
    }

    public Terminal getTerm() {
        return term;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitFNameArg(this, i);
    }
    public Object accept(DecorateASTVisitor visitor, Object o){return visitor.visitFNameArg(this, o);}
}