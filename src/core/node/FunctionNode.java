package core.node;

import core.util.Tokenizer;
import core.util.VariablePool;

import java.util.HashMap;

public class FunctionNode extends Node {

    public static HashMap<String, FunctionNode> functionNodeMap = new HashMap<>();

    public FunctionNode(Tokenizer code) {
        super(code);

        if(code.skip(" "))
            functionNodeMap.put(code.nextName(), this);

    }

    @Override
    public void execute(VariablePool variablePool) {

    }
}
