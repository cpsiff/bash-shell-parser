package bashShell.ast;

import bashShell.DecorateASTVisitor;
import bashShell.DisplayTreeVisitor;
import bashShell.Type;

public class Terminal extends AST {
    private String spelling;
    public Type type;

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
    public Object accept(DecorateASTVisitor visitor, Object o){return visitor.visitTerminal(this, o);}
}