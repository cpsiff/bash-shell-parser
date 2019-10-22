package bashShell;

import java.util.Scanner;
import java.util.ArrayList;

public class MyScanner {

    private int nextToken;

    private ArrayList<Token> tokens = null;

    public MyScanner(String sentence){
        Scanner sent = new Scanner(sentence);
        tokens = new ArrayList<Token>();

        while(sent.hasNext()) {
            String temp = sent.next();
            switch (temp) {
                case "cat":
                case "ls":
                case "pwd":
                case "touch":
                case "cp":
                case "mv":
                case "rm":
                case "chmod":
                case "man":
                case "ps":
                case "bg":
                case "cd":
                case "mkdir":
                case "test":
                    tokens.add(new Token(Token.FName, temp));
                    break;
                case "=":
                    tokens.add(new Token(Token.ASSIGN, temp));
                    break;
                case "if":
                    tokens.add(new Token(Token.IF, temp));
                    break;
                case "then":
                    tokens.add(new Token(Token.THEN, temp));
                    break;
                case "else":
                    tokens.add(new Token(Token.ELSE, temp));
                    break;
                case "fi":
                    tokens.add(new Token(Token.FI, temp));
                    break;
                case "for":
                    tokens.add(new Token(Token.FOR, temp));
                    break;
                case "in":
                    tokens.add(new Token(Token.IN, temp));
                    break;
                case "do":
                    tokens.add(new Token(Token.DO, temp));
                    break;
                case "od":
                    tokens.add(new Token(Token.OD, temp));
                    break;
                case "eol":
                    tokens.add(new Token(Token.EOL, temp));
                    break;
                default:
                    if (temp.matches("[a-zA-Z][a-zA-Z0-9_.]*")){
                        tokens.add(new Token(Token.VAR, temp));
                    }
                    else if(temp.matches("-?[a-zA-Z0-9]*|[0-9]")){
                        tokens.add(new Token(Token.LIT, temp));
                    }
                    else{
                        throw new RuntimeException("Unexpected token: " + temp);
                    }
            }
        }
        for (Token token: tokens){
            System.out.print(token.spelling + " ");
        }
        System.out.println();
        nextToken = 0;
    }

    public Token nextToken() {
        if (nextToken < tokens.size()){
            nextToken++;
            return(tokens.get(nextToken - 1));
        }
        else return new Token(Token.EOT, "eot");
    }
}