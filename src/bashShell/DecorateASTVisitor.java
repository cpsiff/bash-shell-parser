package bashShell;

import bashShell.ast.*;

import java.util.*;

// Example on pg. 159

/**
 * A visitor that decorates and performs contextual analysis on an un-decorated AST
 */
public class DecorateASTVisitor implements Visitor {
    // The ID table to store variables and attributes in
    private IdentificationTable idTable;

    /**
     *  Initialize visitor - Script is passed in when .visitScript(Script s) is called
     */
    public DecorateASTVisitor(){
        this.idTable = new IdentificationTable();
    }

    /**
     * Visit and decorate assignCmd
     * @param assignCmd the assignCmd to be visited
     * @param o any objects to help with visit (should be null)
     * @return always null
     */
    @Override
    public Object visitAssignCmd(AssignCmd assignCmd, Object o) {
        Type rValueType = (Type) assignCmd.getrValue().accept(this, null);

        assignCmd.getlValue().type = rValueType;
        assignCmd.type = rValueType;

        Attribute attr = new Attribute(assignCmd.type);
        idTable.enter(assignCmd.getlValue().getTerm().getSpelling(), attr);

        return null;
    }

    /**
     * Visit and decorate execCmd
     * @param execCmd the execCmd to be visited
     * @param o any objects to help with visit (should be null)
     * @return always null
     */
    @Override
    public Object visitExecCmd(ExecCmd execCmd, Object o) {
        Type cmdType = (Type) execCmd.getCommand().accept(this, o);
        if (!cmdType.equals(Type.executable)){
            System.err.println("Execute command argument is not of type executable");
        }
        execCmd.getArgs().accept(this, o);
        return null;
    }

    /**
     * Visit and decorate fNameArg
     * @param fNameArg the fNameArg to be decorated
     * @param o any objects to help with visit (should be null)
     * @return the type of the fName
     */
    @Override
    public Object visitFNameArg(FNameArg fNameArg, Object o) {
        //return the type of the arg
        Type vType = (Type) fNameArg.getTerm().accept(this, o);
        fNameArg.type = vType;
        return fNameArg.type;
    }

    /**
     * Visit and decorate forCommand
     * @param forCommand the forCommand to be decorated
     * @param o any objects to help with visit (should be null)
     * @return always null
     */
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

    /**
     * Visit and decorate ifCommand
     * @param ifCmd the ifCmd to be decorated
     * @param o any objects to help with visit (should be null)
     * @return always null
     */
    @Override
    public Object visitIfCmd(IfCmd ifCmd, Object o) {
        ifCmd.getCommand().accept(this, o);
        ifCmd.getArgs().accept(this, o);
        ifCmd.getThenBlock().accept(this, o);
        ifCmd.getElseBlock().accept(this, o);
        return null;
    }

    /**
     * Visit and decorate literalArg
     * @param literalArg the literalArg to be decorated
     * @param o any object to help with visit (should be null)
     * @return the type of the literalArg
     */
    @Override
    public Object visitLiteralArg(LiteralArg literalArg, Object o) {
        Type argType = (Type) literalArg.getTerm().accept(this, o);
        literalArg.type = argType;
        // return the type of the arg
        return argType;
    }

    /**
     * Visit nullArg
     * @param nullArg the nullArg
     * @param o should be null
     * @return always Type.nulltype
     */
    @Override
    public Object visitNullArg(NullArg nullArg, Object o) {
        // return the type of the arg
        return Type.nulltype;
    }

    /**
     * Just return null
     * @param nullCmd the nullCmd
     * @param o should be null
     * @return always null
     */
    @Override
    public Object visitNullCmd(NullCmd nullCmd, Object o) {
        return null;
    }

    /**
     * Visit and decorate script (this is the initial call - script is the root of the tree)
     * @param script the script to be decorated
     * @param o any objects to help with visit (should be null)
     * @return always null
     */
    @Override
    public Object visitScript(Script script, Object o) {
        // Check that the script is well formed
        // Return null
        script.getC().accept(this, o);
        return null;
    }

    /**
     * Visit and decorate sequential command
     * @param seqCmd the seqCmd to be visited
     * @param o any object to help with the visit (should be null)
     * @return always null
     */
    @Override
    public Object visitSeqCmd(SeqCmd seqCmd, Object o) {
        // Visit both the commands
        seqCmd.getC1().accept(this, o);
        seqCmd.getC2().accept(this, o);
        return null;
    }

    /**
     * Visit Terminal and get its type
     * @param terminal the terminal to be visited
     * @param o any objects to help with the visit (should be null)
     * @return the type of the terminal
     */
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

    /**
     * Visit and decorate varArg
     * @param varArg the varArg to be visited
     * @param o any objects to help with visit (should be null)
     * @return the type of the varArg
     */
    @Override
    public Object visitVarArg(VarArg varArg, Object o) {
        //return the type of the arg
        Type vType = (Type) varArg.getTerm().accept(this, o);
        varArg.type = vType;
        return varArg.type;
    }

    /**
     * Visit and decorate seqArg
     * @param arg the seqArg to be visited
     * @param o any objects to help with visit (should be null)
     * @return always null
     */
    public Object visitSeqArg(SeqArg arg, Object o){
        // Visit both of the args
        arg.getArg1().accept(this, o);
        arg.getArg2().accept(this, o);
        return null;
    }
}
