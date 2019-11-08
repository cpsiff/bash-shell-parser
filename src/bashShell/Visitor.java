package bashShell;

import bashShell.ast.*;

public interface Visitor {
    String visitAssignCmd(AssignCmd assignCmd, int i);
    String visitExecCmd(ExecCmd execCmd, int i);
    String visitFNameArg(FNameArg fNameArg, int i);
    String visitForCommand(ForCommand forCommand, int i);
    String visitIfCmd(IfCmd ifCmd, int i);
    String visitLiteralArg(LiteralArg literalArg, int i);
    String visitNullArg(NullArg nullArg, int i);
    String visitNullCmd(NullCmd nullCmd, int i);
    String visitScript(Script script, int i);
    String visitSeqCmd(SeqCmd seqCmd, int i);
    String visitTerminal(Terminal terminal, int i);
    String visitVarArg(VarArg varArg, int i);
}
