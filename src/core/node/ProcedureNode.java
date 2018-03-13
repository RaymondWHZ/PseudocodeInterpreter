package core.node;

import core.util.Tokenizer;
import core.util.VariablePool;

import java.util.HashMap;

public class ProcedureNode extends Node {

    public static HashMap<String, ProcedureNode> procedureNodeMap = new HashMap<>();

    public ProcedureNode(Tokenizer code) {
        super(code);

        if(code.skip(" "))
            procedureNodeMap.put(code.nextName(), this);

    }

    @Override
    public void execute(VariablePool variablePool) {

    }
}
