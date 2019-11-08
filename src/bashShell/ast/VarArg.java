package bashShell.ast;

import bashShell.DisplayTreeVisitor;

public class VarArg extends SingleArg {
    private Terminal variable;

    public VarArg(Terminal variable){
        this.variable = variable;
    }

    public Terminal getVariable() {
        return variable;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitVarArg(this, i);
    }
}