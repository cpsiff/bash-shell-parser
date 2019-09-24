package bashShell;

public class Parser {
    private Byte currentToken = null;
    private MyScanner myScanner = null;

    private boolean errorOccurred = false;

    //------------- Utility Methods -------------

    /**
     * Accept a specified token if it matches the
     * current Token.  Acceptance entails setting
     * currentToken to the next token in the input
     * stream.
     *
     * @param expectedKind The expected type of token.
     */
    private void accept(byte expectedKind) {
        if (currentToken == expectedKind)
            currentToken = myScanner.nextToken();
        else {
            writeError("Expected:  " + Token.kindString(expectedKind) +
                    "Found: " + Token.kindString(currentToken));
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

    private void writeError(String s) {
        System.out.println(s);
    }

    //---------------- Public Methods ----------------

    public Parser(String sentence){
        myScanner = new MyScanner(sentence);
        currentToken = myScanner.nextToken();
        parseScript();
        if (!errorOccurred) System.out.println("Script Accepted");
    }

    //---------------- Parsing Methods ---------------
    private void parseScript() {
        while (currentToken == Token.FName
                || currentToken == Token.VAR
                || currentToken == Token.IF
                || currentToken == Token.FOR)
            parseCommand();
    }

    private void parseCommand() {
        switch (currentToken) {
            case Token.FName: {
                parseFileName();
                // while in the argument, which can go to FName, LIT, or VAR
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR)
                    parseArgument();
                accept(Token.EOL);
                break;
            }
            case Token.VAR: {
                parseVariable();
                accept(Token.ASSIGN);
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR)
                    parseArgument();
                accept(Token.EOL);
                break;
            }
            case Token.IF: {
                acceptIt();
                parseFileName();
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR)
                    parseArgument();
                accept(Token.THEN);
                accept(Token.EOL);
                while(currentToken == Token.FName
                        || currentToken == Token.VAR
                        || currentToken == Token.IF
                        || currentToken == Token.FOR)
                    parseCommand();
                accept(Token.ELSE);
                accept(Token.EOL);
                while(currentToken == Token.FName
                        || currentToken == Token.VAR
                        || currentToken == Token.IF
                        || currentToken == Token.FOR)
                    parseCommand();
                accept(Token.FI);
                accept(Token.EOL);
                break;
            }
            case Token.FOR: {
                acceptIt();
                parseVariable();
                accept(Token.IN);
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR)
                    parseArgument();
                accept(Token.EOL);
                accept(Token.DO);
                while(currentToken == Token.FName
                        || currentToken == Token.VAR
                        || currentToken == Token.IF
                        || currentToken == Token.FOR)
                    parseCommand();
                accept(Token.OD);
                accept(Token.EOL);
                break;
            }
        }
    }

    private void parseArgument() {
        switch(currentToken) {
            case Token.FName: {
                parseFileName();
                break;
            }
            case Token.LIT: {
                parseLiteral();
                break;
            }
            case Token.VAR: {
                parseVariable();
                break;
            }
        }
    }

    private void parseFileName() {
        acceptIt();
    }

    private void parseLiteral() {
        acceptIt();
    }

    private void parseVariable() {
        acceptIt();
    }
}