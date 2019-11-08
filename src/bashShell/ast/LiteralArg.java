package bashShell.ast;

import bashShell.DisplayTreeVisitor;

public class LiteralArg extends SingleArg {
    private Terminal literal;

    public LiteralArg(Terminal literal){
        this.literal = literal;
    }

    public Terminal getLiteral() {
        return literal;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitLiteralArg(this, i);
    }
}