package bashShell.ast;

import bashShell.DisplayTreeVisitor;

public class Terminal extends AST {
    private String spelling;

    public Terminal(String spelling){
        this.spelling = spelling;
    }

    public String getSpelling() {
        return spelling;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitTerminal(this, i);
    }
}