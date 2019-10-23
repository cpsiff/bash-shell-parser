package bashShell.ast;

/**
 * Utility class for visiting nodes
 */
public class Util {
    /**
     * For use creating indentation in tree output
     * @param length the indentation amount
     * @return a string of length*2 spaces
     */
    public static String s(int length){
        length = length*2; //set indent to two spaces
        StringBuilder outputBuffer = new StringBuilder(length);
        for (int i = 0; i < length; i++){
            outputBuffer.append(" ");
        }
        return outputBuffer.toString();
    }
}
