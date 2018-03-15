package core.node;

import core.util.Tokenizer;

import java.util.HashMap;

/**
 * 代表整个程序
 */
public class ProgramNode extends Node {

    private BlockNode blockNode;

    public ProgramNode(Tokenizer code) {
        HashMap<String, NodeFactory> presetMap = new HashMap<>();

        // 给代码块节点加入子程序关键字判断条目
        presetMap.put("PROCEDURE", () -> new ProcedureNode(code));
        presetMap.put("FUNCTION", () -> new FunctionNode(code));

        blockNode = new BlockNode(code, presetMap);
    }

    public void execute() {
        blockNode.execute(new NodePool());
    }

    /**
     * 在ProgramNode里面调用执行的时候，并不存在上层的变量池，所以不应当使用。
     */
    @Deprecated
    public void execute(NodePool pool) {
        execute();
    }
}
