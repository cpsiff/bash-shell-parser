package bashShell;

import java.util.Scanner;
import java.util.ArrayList;

public class MyScanner {

    private int nextToken;

    private ArrayList<Byte> tokens = null;

    public MyScanner(String sentence){
        Scanner sent = new Scanner(sentence);
        tokens = new ArrayList<Byte>();

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
                    tokens.add(Token.FName);
                    break;
                case "=":
                    tokens.add(Token.ASSIGN);
                    break;
                case "if":
                    tokens.add(Token.IF);
                    break;
                case "then":
                    tokens.add(Token.THEN);
                    break;
                case "else":
                    tokens.add(Token.ELSE);
                    break;
                case "fi":
                    tokens.add(Token.FI);
                    break;
                case "for":
                    tokens.add(Token.FOR);
                    break;
                case "in":
                    tokens.add(Token.IN);
                    break;
                case "do":
                    tokens.add(Token.DO);
                    break;
                case "od":
                    tokens.add(Token.OD);
                    break;
                case "eol":
                    tokens.add(Token.EOL);
                    break;
                default:
                    if (temp.matches("[a-zA-Z][a-zA-Z0-9_.]*")){
                        tokens.add(Token.VAR);
                    }
                    else if(temp.matches("-?[a-zA-Z0-9]*|[0-9]")){
                        tokens.add(Token.LIT);
                    }
                    else{
                        throw new RuntimeException("Unexpected token: " + temp);
                    }
            }
        }
        System.out.println(tokens);
        nextToken = 0;
    }

    public Byte nextToken() {
        if (nextToken < tokens.size()){
            nextToken++;
            return(tokens.get(nextToken - 1));
        }
        else return Token.EOT;
    }

    public static void main(String [] args) {
        // -------------- TESTS ----------------

        //Parser ts = new Parser("ls -al eol mv this that eol touch myNewFile eol mv myNewFile newStuff eol cd newStuff eol chmod 577 eol");
        //Parser ts = new Parser("if cat file1 file2 then eol else eol fi eol");
        //Parser ts = new Parser("for apples in fruit eol do eol mv apples basket eol od eol");
        //Parser ts = new Parser("if test -e apples then eol touch apples eol else eol fi eol");

        // ------------- PROGRAM ----------------

        while(true) {
            Scanner in = new Scanner(System.in);
            System.out.println("Type script below with spaces between tokens, and eol as an end of line, do not return after end of line");
            System.out.print(">>> ");
            String sentence = in.nextLine();

            Parser ts = new Parser(sentence);
        }
    }
}