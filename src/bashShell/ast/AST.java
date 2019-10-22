package bashShell.ast;

/**
 * All AST classes extend AST so that we can enforce that visit is implemented in all of them
 */
public abstract class AST {
    protected abstract void visit();
}