package core.node;

import core.interpreter.Interpreter;
import core.util.SyntaxException;
import core.util.Tokenizer;

class OutputNode extends Node {

    private String text;

    public OutputNode(Tokenizer code) {
        if (!code.skip(" "))
            throw new SyntaxException("There should be a whitespace after OUTPUT keyword!");

        String nextName;
        if ((nextName = code.nextName()).length() > 0) {
            // TODO 如果是表达式，怎么处理
        }

        if (code.nextChar() == '\"') {
            // 如果是字符串，就这样干
            text = code.readTill('\"');
            if(!code.skip("\""))
                throw new SyntaxException("Missing a \'\"\'!");
        }
    }

    @Override
    public void execute(NodePool pool) {
        Interpreter.output(text);
    }
}
