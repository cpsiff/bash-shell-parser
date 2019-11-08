package bashShell.ast;

import bashShell.DisplayTreeVisitor;

/**
 * All AST classes extend AST so that we can enforce that visit is implemented in all of them
 */
public abstract class AST {
    public abstract String accept(DisplayTreeVisitor visitor, int i);
}