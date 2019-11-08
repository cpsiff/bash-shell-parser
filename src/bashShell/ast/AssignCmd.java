package bashShell.ast;

import bashShell.DisplayTreeVisitor;

public class AssignCmd extends Command {
    private VarArg lValue;
    private SingleArg rValue;

    public AssignCmd(VarArg lValue, SingleArg rValue){
        this.lValue = lValue;
        this.rValue = rValue;
    }

    public SingleArg getrValue() {
        return rValue;
    }

    public VarArg getlValue() {
        return lValue;
    }

    @Override
    public String accept(DisplayTreeVisitor visitor, int i) {
        return visitor.visitAssignCmd(this, i);
    }
}