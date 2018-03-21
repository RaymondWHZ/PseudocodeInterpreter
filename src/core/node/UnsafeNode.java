package core.node;

import core.util.RunningException;

/**
 * 如果一个节点在执行的时候可能抛出异常（如变量引用），最好继承这个类并重写securedExecute以自动给抛出的异常追加错误行数
 */
public class UnsafeNode extends Node {

    private final int lineNumber;

    public UnsafeNode(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public final void execute(NodePool pool) {
        try {
            securedExecute(pool);
        } catch (Exception e) {
            throw new RunningException(e, lineNumber);
        }
    }

    protected void securedExecute(NodePool pool) throws Exception { }
}
