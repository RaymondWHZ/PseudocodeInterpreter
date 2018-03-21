package core.node;

import core.util.SyntaxException;
import core.util.Tokenizer;

import java.util.HashMap;

/**
 * 根据第一个词路由至需要的节点
 */
class LineNode extends Node {

    private Node node;

    public LineNode(Tokenizer code, HashMap<String, NodeFactory> grammarMap) {
        String nextWord = code.nextName();
        if (grammarMap.containsKey(nextWord)) {
            node = grammarMap.get(nextWord).create();
        }
        else if (code.skip("<-"))
            ;  // TODO 调用处理变量赋值的类
        else
            throw new SyntaxException("Syntax not found: " + nextWord, code);
        code.skipWhitespaces();
    }

    @Override
    public void execute(NodePool pool) {
        node.execute(pool);
    }
}
