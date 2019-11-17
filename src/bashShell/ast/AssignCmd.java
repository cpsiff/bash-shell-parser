package bashShell.ast;

import bashShell.DecorateASTVisitor;
import bashShell.DisplayTreeVisitor;
import bashShell.Type;

public class AssignCmd extends Command {
    private VarArg lValue;
    private SingleArg rValue;
    public Type type;

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
    public Object accept(DecorateASTVisitor visitor, Object o){return visitor.visitAssignCmd(this, o);}
}