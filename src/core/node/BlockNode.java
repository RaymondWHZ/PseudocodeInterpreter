package core.node;

import core.util.Tokenizer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 带有多行代码的代码块，功能为确定作用域和控制逐行解析执行
 */
class BlockNode extends Node{

    private final ArrayList<Node> lineNodeList = new ArrayList<>();

    public BlockNode(Tokenizer code) {
        this(code, null);
    }

    public BlockNode(Tokenizer code, HashMap<String, NodeFactory> extraMap) {
        if (extraMap == null)
            extraMap = new HashMap<>();

        while (code.hasNext()) {
            lineNodeList.add(new LineNode(code, extraMap));
        }
    }

    @Override
    public void execute(NodePool pool) {
        pool.setUpRange();

        for (Node node: lineNodeList) {
            if (node != null)
                node.execute(pool);
        }

        pool.tearDownRange();
    }
}
