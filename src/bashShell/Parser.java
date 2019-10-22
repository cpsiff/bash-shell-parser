package bashShell;
import bashShell.ast.*;

public class Parser {
    private Token currentToken = null;
    private MyScanner myScanner = null;
    private static Script scriptAST = null;

    // Keep track of whether an error has occurred to know whether to accept string or not
    private boolean errorOccurred = false;

    //------------- Utility Methods -------------//

    /**
     * Accept a specified token if it matches the current Token.  Acceptance entails setting currentToken to the next
     * token in the input stream.
     * @param expectedKind The expected type of token.
     */
    private void accept(byte expectedKind) {
        if (currentToken.kind == expectedKind)
            currentToken = myScanner.nextToken();
        else {
            writeError("Expected:  " + Token.kindString(expectedKind) +
                    "  Found: " + Token.kindString(currentToken.kind) + " : " + currentToken.spelling);
            errorOccurred = true;
        }

    }

    /**
     * Accept the current token by setting currentToken
     * to the next token in the input stream.
     */
    private void acceptIt() {
        currentToken = myScanner.nextToken();
    }

    /**
     * Write an error to the console
     * @param s the error string to write
     */
    private void writeError(String s) {
        System.out.println(s);
    }

    //---------------- Public Methods ----------------//

    public Parser(String sentence){
        myScanner = new MyScanner(sentence);
        currentToken = myScanner.nextToken();
        scriptAST = parseScript();
    }

    /**
     * Display the AST starting at the script by recursively visiting each node in the tree
     * Outputs to System.out
     */
    public static String displayAST(){
        return scriptAST.visit(0);
    }

    //------------- Parser Methods -------------//

    /**
     * Parse the input program using recursive descent.
     * Each method accepts the appropriate tokens and calls the appropriate other parse methods
     * @return each parse method returns an object of the appropriate type, according to the abstract syntax
     */
    private Script parseScript() {
        while (currentToken.kind == Token.FName
                || currentToken.kind == Token.VAR
                || currentToken.kind == Token.IF
                || currentToken.kind == Token.FOR)
            return new Script(parseCommand());
        return null;
    }

    private Command parseCommand() {
        Command commandAST = null;
        switch (currentToken.kind) {
            case Token.FName: {
                FNameArg fnameArg = parseFileName();
                Argument argument = null;
                // while in the argument, which can go to FName, LIT, or VAR
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR)
                    argument = parseArgument();
                accept(Token.EOL);
                commandAST = new ExecCmd(fnameArg, argument);
                break;
            }
            case Token.VAR: {
                VarArg varArg = parseVariable();
                accept(Token.ASSIGN);
                SingleArg singleArg = parseSingleArgument(); //TODO
                accept(Token.EOL);
                commandAST = new AssignCmd(varArg, singleArg);
                break;
            }
            case Token.IF: {
                FNameArg fNameArg = parseFileName();
                Argument argument = null;
                Command ifBlock = null;
                Command elseBlock = null;
                acceptIt();
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR)
                    argument = parseArgument();
                accept(Token.THEN);
                accept(Token.EOL);
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.VAR
                        || currentToken.kind == Token.IF
                        || currentToken.kind == Token.FOR)
                    ifBlock = parseCommand();
                accept(Token.ELSE);
                accept(Token.EOL);
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.VAR
                        || currentToken.kind == Token.IF
                        || currentToken.kind == Token.FOR)
                    elseBlock = parseCommand();
                accept(Token.FI);
                accept(Token.EOL);
                commandAST = new IfCmd(fNameArg, argument, ifBlock, elseBlock);
                break;
            }
            case Token.FOR: {
                acceptIt();
                VarArg varArg = parseVariable();
                Argument argument = null;
                Command command = null;
                accept(Token.IN);
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR)
                    argument = parseArgument();
                accept(Token.EOL);
                accept(Token.DO);
                accept(Token.EOL);
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.VAR
                        || currentToken.kind == Token.IF
                        || currentToken.kind == Token.FOR)
                    command = parseCommand();
                accept(Token.OD);
                accept(Token.EOL);
                commandAST = new ForCommand(varArg, argument, command);
                break;
            }
            default: {
                System.err.println("Error in parseCommand: FName, VAR, IF, or FOR not found");
            }
        }
        if(currentToken.kind != Token.EOT){
            Command command2;
            command2 = parseCommand();
            return new SeqCmd(commandAST, command2);
        }
        return commandAST;
    }

    private Argument parseArgument() { //TODO: make this allow for multiple arguments?
        switch(currentToken.kind) {
            case Token.FName: {
                return parseFileName();
            }
            case Token.LIT: {
                return parseLiteral();
            }
            case Token.VAR: {
                return parseVariable();
            }
        }
        return null;
    }

    // A separate parseSingleArgument method is implemented to ensure that single arguments rules are enforced
    private SingleArg parseSingleArgument() {
        switch(currentToken.kind) {
            case Token.FName: {
                return parseFileName();
            }
            case Token.LIT: {
                return parseLiteral();
            }
            case Token.VAR: {
                return parseVariable();
            }
        }
        return null;
    }

    private FNameArg parseFileName() {
        Terminal term = new Terminal(currentToken.spelling);
        acceptIt();
        return new FNameArg(term);
    }

    private LiteralArg parseLiteral() {
        Terminal term = new Terminal(currentToken.spelling);
        acceptIt();
        return new LiteralArg(term);
    }

    private VarArg parseVariable() {
        Terminal term = new Terminal(currentToken.spelling);
        acceptIt();
        return new VarArg(term);
    }
}