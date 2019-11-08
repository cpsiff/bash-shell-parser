package bashShell;

import bashShell.ast.*;

public class DisplayTreeVisitor implements Visitor{

    /**
     * For use creating indentation in tree output
     * @param length the indentation amount
     * @return a string of length*2 spaces
     */
    private static String s(int length){
        length = length*2; //set indent to two spaces
        StringBuilder outputBuffer = new StringBuilder(length);
        for (int i = 0; i < length; i++){
            outputBuffer.append(" ");
        }
        return outputBuffer.toString();
    }

    public String visitAssignCmd(AssignCmd assignCmd, int i){
        return(s(i) + "AssignCmd\n" + visitVarArg(assignCmd.getlValue(), i+1) + visitArgument(assignCmd.getrValue(), i+1));
    }

    public String visitExecCmd(ExecCmd execCmd, int i){
        return(s(i) + "ExecCmd\n" + visitArgument(execCmd.getCommand(), i+1) + visitArgument(execCmd.getArgs(), i+1));
    }

    public String visitFNameArg(FNameArg fNameArg, int i){
        return(s(i) + "FNameArg\n" + visitTerminal(fNameArg.getTerm(), i+1));
    }

    public String visitForCommand(ForCommand forCommand, int i){
        return(s(i) + "ForCommand\n" + visitVarArg(forCommand.getVar(), i+1) + visitArgument(forCommand.getArgs(), i+1) + visitCommand(forCommand.getDoBody(), i+1));
    }

    public String visitIfCmd(IfCmd ifCmd, int i){
        return(s(i) + "IfCmd\n" + visitArgument(ifCmd.getCommand(), i+1) + visitArgument(ifCmd.getArgs(), i+1) + visitCommand(ifCmd.getThenBlock(), i+1) + visitCommand(ifCmd.getElseBlock(), i+1));
    }

    public String visitLiteralArg(LiteralArg literalArg, int i){
        return(s(i) + "LiteralArg\n" + visitTerminal(literalArg.getLiteral(), i+1));
    }

    public String visitNullArg(NullArg nullArg, int i){
        return(s(i) + "NullArg\n");
    }

    public String visitNullCmd(NullCmd nullCmd, int i){
        return(s(i) + "NullCmd\n");
    }

    public String visitScript(Script script, int i){
        return(s(i) + "Script\n" + visitCommand(script.getC(), i+1));
    }

    public String visitSeqArg(SeqArg seqArg, int i){
        return(s(i) + "SeqArg\n" + visitArgument(seqArg.getArg1(), i+1) + visitArgument(seqArg.getArg2(), i+1));
    }

    public String visitSeqCmd(SeqCmd seqCmd, int i){
        return(s(i) + "SeqCmd\n" + visitCommand(seqCmd.getC1(), i+1) + visitCommand(seqCmd.getC2(), i+1));
    }

    public String visitTerminal(Terminal terminal, int i){
        return(s(i) + "Terminal (" + terminal.getSpelling() + ")\n");
    }

    public String visitVarArg(VarArg varArg, int i){
        return(s(i) + "VarArg\n" + visitTerminal(varArg.getVariable(), i+1));
    }

    public String visitCommand(Command command, int i){
        return command.accept(this, i);
    }

    public String visitArgument(Argument argument, int i){
        return argument.accept(this, i);
    }
}
