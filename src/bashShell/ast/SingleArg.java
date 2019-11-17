package bashShell.ast;

import bashShell.Type;

/**
 * The three acceptable single argument subtypes extend singleArg
 */
public abstract class SingleArg extends Argument {
    public Type type;
    abstract public Terminal getTerm();
}