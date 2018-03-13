package core.node;

import core.util.SyntaxException;
import core.util.Tokenizer;
import core.util.VariablePool;

import java.util.ArrayList;
import java.util.HashMap;

public class BlockNode extends Node{

    public final VariablePool variablePool = new VariablePool();

    public final HashMap<String, NodeFactory> nodeFactoryMap = new HashMap<>();

    public final ArrayList<Node> commandNodeList = new ArrayList<>();

    public BlockNode(Tokenizer code) {
        super(code);

        setupFactoryMap(code);

        while (code.hasNext()) {
            String nextWord = code.nextName();
            if (nodeFactoryMap.containsKey(nextWord)) {
                Node newNode = nodeFactoryMap.get(nextWord).create();
                if (newNode != null)
                    commandNodeList.add(newNode);
            }
            else
                throw new SyntaxException("Syntax not found: " + nextWord);
            code.skipWhitespaces();
        }
    }

    protected void setupFactoryMap(Tokenizer code) {
        nodeFactoryMap.put("OUTPUT", () -> new OutputNode(code));
    }

    @Override
    public void execute(VariablePool variablePool) {
        this.variablePool.outer = variablePool;

        for (Node node: commandNodeList) {
            if (node != null)
                node.execute(this.variablePool);
        }
    }
}
