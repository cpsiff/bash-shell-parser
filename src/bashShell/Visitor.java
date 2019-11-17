package bashShell;

import bashShell.ast.*;

public interface Visitor {
    Object visitAssignCmd(AssignCmd assignCmd, Object o);
    Object visitExecCmd(ExecCmd execCmd, Object o);
    Object visitFNameArg(FNameArg fNameArg, Object o);
    Object visitForCommand(ForCommand forCommand, Object o);
    Object visitIfCmd(IfCmd ifCmd, Object o);
    Object visitLiteralArg(LiteralArg literalArg, Object o);
    Object visitNullArg(NullArg nullArg, Object o);
    Object visitNullCmd(NullCmd nullCmd, Object o);
    Object visitScript(Script script, Object o);
    Object visitSeqCmd(SeqCmd seqCmd, Object o);
    Object visitTerminal(Terminal terminal, Object o);
    Object visitVarArg(VarArg varArg, Object o);
}
