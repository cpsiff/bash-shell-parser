package bashShell;

import bashShell.ast.*;

import java.util.*;

// Example on pg. 159

public class DecorateASTVisitor implements Visitor {
    private IdentificationTable idTable;

    public DecorateASTVisitor(){
        this.idTable = new IdentificationTable();
    }

    @Override
    public Object visitAssignCmd(AssignCmd assignCmd, Object o) {
        Type rValueType = (Type) assignCmd.getrValue().accept(this, null);

        assignCmd.getlValue().type = rValueType;
        assignCmd.type = rValueType;

        Attribute attr = new Attribute(assignCmd.type);
        idTable.enter(assignCmd.getlValue().getTerm().getSpelling(), attr);

        return null;
    }

    @Override
    public Object visitExecCmd(ExecCmd execCmd, Object o) {
        Type cmdType = (Type) execCmd.getCommand().accept(this, o);
        if (!cmdType.equals(Type.executable)){
            System.err.println("Execute command argument is not of type executable");
        }
        execCmd.getArgs().accept(this, o);
        return null;
    }

    @Override
    public Object visitFNameArg(FNameArg fNameArg, Object o) {
        //return the type of the arg
        Type vType = (Type) fNameArg.getTerm().accept(this, o);
        fNameArg.type = vType;
        return fNameArg.type;
    }

    @Override
    public Object visitForCommand(ForCommand forCommand, Object o) {
        forCommand.getVar().accept(this, o);

        idTable.openScope();

        Attribute attr = new Attribute(forCommand.getVar().type);
        idTable.enterScoped(forCommand.getVar().getTerm().getSpelling(), attr);

        forCommand.getArg().accept(this, o);
        forCommand.getDoBody().accept(this, o);

        idTable.closeScope();

        return null;
    }

    @Override
    public Object visitIfCmd(IfCmd ifCmd, Object o) {
        ifCmd.getCommand().accept(this, o);
        ifCmd.getArgs().accept(this, o);
        ifCmd.getThenBlock().accept(this, o);
        ifCmd.getElseBlock().accept(this, o);
        return null;
    }

    @Override
    public Object visitLiteralArg(LiteralArg literalArg, Object o) {
        Type argType = (Type) literalArg.getTerm().accept(this, o);
        literalArg.type = argType;
        // return the type of the arg
        return argType;
    }

    @Override
    public Object visitNullArg(NullArg nullArg, Object o) {
        // return the type of the arg
        return Type.nulltype;
    }

    @Override
    public Object visitNullCmd(NullCmd nullCmd, Object o) {
        return null;
    }

    @Override
    public Object visitScript(Script script, Object o) {
        // Check that the script is well formed
        // Return null
        script.getC().accept(this, o);
        return null;
    }

    @Override
    public Object visitSeqCmd(SeqCmd seqCmd, Object o) {
        // Visit both the commands
        seqCmd.getC1().accept(this, o);
        seqCmd.getC2().accept(this, o);
        return null;
    }

    @Override
    public Object visitTerminal(Terminal terminal, Object o) {
        // Return the type of the terminal
        String spelling = terminal.getSpelling();
        List<String> executables = new ArrayList<>(Arrays.asList("cat", "ls", "pwd", "touch", "cp", "mv", "rm", "chmod", "man", "ps", "bg", "mkdir", "test", "cd"));
        if (executables.contains(spelling)){
            terminal.type = Type.executable;
        }
        else{
            terminal.type = Type.numeric; // assume it's a numeric type
            List<Character> ints = new ArrayList<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
            for (char ch: spelling.toCharArray()){
                if (!ints.contains(ch)) {
                    terminal.type = Type.string; // if it contains something other than a number, it's a string type
                    break;
                }
            }
        }
        return terminal.type;
    }

    @Override
    public Object visitVarArg(VarArg varArg, Object o) {
        //return the type of the arg
        Type vType = (Type) varArg.getTerm().accept(this, o);
        varArg.type = vType;
        return varArg.type;
    }

    public Object visitSeqArg(SeqArg arg, Object o){
        // Visit both of the args
        arg.getArg1().accept(this, o);
        arg.getArg2().accept(this, o);
        return null;
    }
}
