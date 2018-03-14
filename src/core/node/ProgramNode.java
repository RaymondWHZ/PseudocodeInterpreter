package core.node;

import core.util.Tokenizer;

import java.util.HashMap;

/**
 * 代表整个程序，给代码块节点加入子程序关键字判断条目
 */
public class ProgramNode extends Node {

    private BlockNode blockNode;

    public ProgramNode(Tokenizer code) {
        HashMap<String, NodeFactory> extraMap = new HashMap<>();

        extraMap.put("PROCEDURE", () -> {
            new ProcedureNode(code);
            return null;
        });
        extraMap.put("FUNCTION", () -> {
            new FunctionNode(code);
            return null;
        });

        blockNode = new BlockNode(code, extraMap);
    }

    public void execute() {
        blockNode.execute(new Pool());
    }

    /**
     * 在ProgramNode里面调用执行的时候，并不存在上层的变量池，所以不应当使用。
     */
    @Deprecated
    public void execute(Pool pool) {
        execute();
    }
}
