package core.node;

import core.util.SyntaxException;
import core.util.Tokenizer;

import java.util.HashMap;

/**
 * 根据第一个词路由至需要的节点
 */
class LineNode extends Node {

    private Node node;

    LineNode(Tokenizer code, HashMap<String, NodeFactory> extraMap) {
        // 将所有的节点可能性全部放入数组
        // TODO 思考这里只通过检测第一个词来路由的方式是否合理，如果不合理就需要重新设计
        extraMap.put("OUTPUT", () -> new OutputNode(code));

        String nextWord = code.nextName();
        if (extraMap.containsKey(nextWord)) {
            node = extraMap.get(nextWord).create();
        }
        else if (code.skip("<-"))
            ;  // TODO 调用处理变量赋值的类
        else
            throw new SyntaxException("Syntax not found: " + nextWord);
        code.skipWhitespaces();
    }

    @Override
    void execute(Pool pool) {
        node.execute(pool);
    }
}
