package bashShell.ast;

import bashShell.DecorateASTVisitor;
import bashShell.DisplayTreeVisitor;
import bashShell.Type;

public class VarArg extends SingleArg {
    private Terminal variable;
    public Type type;

    public VarArg(Terminal variable){
        this.variable = variable;
    }

    public Terminal getTerm() {
        return variable;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitVarArg(this, i);
    }
    public Object accept(DecorateASTVisitor visitor, Object o){return visitor.visitVarArg(this, o);}

}