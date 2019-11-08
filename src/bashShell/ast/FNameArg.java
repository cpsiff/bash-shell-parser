package bashShell.ast;

import bashShell.DisplayTreeVisitor;

public class FNameArg extends SingleArg  {
    private Terminal term;

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
}