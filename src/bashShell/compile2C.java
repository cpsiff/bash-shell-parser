package bashShell;

import java.util.Scanner;
import java.io.File;

/**
 * This class is called from the command line to parse a sentence into an AST
 *
 * Command line parameters (no order):
 *      (Required) The name of the file, e.g. /home/carter/IdeaProjects/bash-sub-shell/test.sh
 *      (Optional) -p to output AST to file
 *      (Optional) -d to print AST to console
 */
public class compile2C {
    public static void main(String [] args) throws Exception{
        // -------------- TESTS ----------------//

        //Parser ts = new Parser("ls -al eol mv this that eol touch myNewFile eol mv myNewFile newStuff eol cd newStuff eol chmod 577 eol");
        //Parser ts = new Parser("if cat file1 file2 then eol else eol fi eol");
        //Parser ts = new Parser("for apples in fruit eol do eol mv apples basket eol od eol");
        //Parser ts = new Parser("if test -e apples then eol touch apples eol else eol fi eol");

        // ------------- PROGRAM ----------------//

        // Flags to keep track of what the user would like to do
        boolean outputToFile = false;
        boolean outputToScreen = false;
        String inputFileName = "";

        for(String arg: args) {
            if (arg.equals("-p")){
                outputToFile = true;
            }
            else if (arg.equals("-d")){
                outputToScreen = true;
            }
            else{
                inputFileName = arg;
            }
        }

        // Read in input from file
        File file = new File(inputFileName);
        Scanner sc = new Scanner(file);
        StringBuilder inputString = new StringBuilder();
        while (sc.hasNextLine())
            inputString.append(sc.nextLine()).append(" eol "); //Manually add an eol token to the end of each line

        // Create a parser object, which will create an AST
        Parser ts = new Parser(inputString.toString());

        if(outputToScreen) {
            Parser.displayAST();
        }
    }
}
