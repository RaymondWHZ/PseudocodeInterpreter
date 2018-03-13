package core.node;

import core.interpretor.Interpretor;
import core.util.SyntaxException;
import core.util.Tokenizer;
import core.util.VariablePool;

public class OutputNode extends Node {

    String text;

    public OutputNode(Tokenizer code) {
        super(code);

        if (!code.skip(" "))
            throw new SyntaxException("There should be a whitespace after OUTPUT keyword!");

        String nextName;
        if ((nextName = code.nextName()).length() > 0) {
            // TODO 如果是表达式，怎么处理
        }

        if (code.nextChar() == '\"') {
            text = code.readTill('\"');
            code.skip("\"");
        }
    }

    @Override
    public void execute(VariablePool variablePool) {
        Interpretor.output(text);
    }
}
