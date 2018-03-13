package core.node;

import core.util.Tokenizer;
import core.util.VariablePool;

public class ProgramNode extends BlockNode {

    public ProgramNode(Tokenizer code) {
        super(code);
    }

    @Override
    protected void setupFactoryMap(Tokenizer code) {
        super.setupFactoryMap(code);

        nodeFactoryMap.put("PROCEDURE", () -> {
            new ProcedureNode(code);
            return null;
        });
        nodeFactoryMap.put("FUNCTION", () -> {
            new FunctionNode(code);
            return null;
        });
    }

    public void execute() {
        super.execute(null);
    }

    /**
     * 在ProgramNode里面调用执行的时候，并不存在上层的变量池，所以不应当使用。
     */
    @Deprecated
    public void execute(VariablePool variablePool) {
        execute();
    }
}
