package core.node;

import core.util.SyntaxException;
import core.util.Tokenizer;

import java.util.HashMap;

/**
 * 根据第一个词路由至需要的节点
 */
class LineNode extends Node {

    private Node node;

    public LineNode(Tokenizer code, HashMap<String, NodeFactory> presetMap) {
        // 将所有的节点可能性全部放入数组
        // TODO 思考这里只通过检测第一个词来路由的方式是否合理，如果不合理就需要重新设计
        presetMap.put("OUTPUT", () -> new OutputNode(code));

        String nextWord = code.nextName();
        if (presetMap.containsKey(nextWord)) {
            node = presetMap.get(nextWord).create();
        }
        else if (code.skip("<-"))
            ;  // TODO 调用处理变量赋值的类
        else
            throw new SyntaxException("Syntax not found: " + nextWord);
        code.skipWhitespaces();
    }

    @Override
    public void execute(NodePool pool) {
        node.execute(pool);
    }
}
