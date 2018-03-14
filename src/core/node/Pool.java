package core.node;

import java.util.HashMap;

/**
 * 存储了所有创建的变量和子程序的池
 */
class Pool {

    private VariablePool variablePool = new VariablePool(null);
    private final HashMap<String, ProcedureNode> procedures = new HashMap<>();
    private final HashMap<String, FunctionNode> functions = new HashMap<>();

    public void setUpRange() {
        variablePool = new VariablePool(variablePool);
    }

    public void tearDownRange() {
        VariablePool outer = variablePool.outer;
        if (outer != null)
            variablePool = outer;
    }

    public void createVariable(String name, Object variable) {
        variablePool.put(name, variable);
    }

    public Object getVariable(String name) {
        return variablePool.get(name);
    }

    public void createProcedure(String name, ProcedureNode procedure) {
        procedures.put(name, procedure);
    }

    public ProcedureNode getProcedure(String name) {
        return procedures.get(name);
    }

    public void createFunction(String name, FunctionNode function) {
        functions.put(name, function);
    }

    public FunctionNode getFunction(String name) {
        return functions.get(name);
    }
}

/**
 * 以链表形式实现作用域区分的变量专用池
 */
class VariablePool {

    final VariablePool outer;

    private final HashMap<String, Object> variables = new HashMap<>();

    VariablePool(VariablePool outer) {
        this.outer = outer;
    }

    public void put(String name, Object value) {
        variables.put(name, value);
    }

    public Object get(String name) {
        if (variables.containsKey(name)) {
            Object variable = variables.get(name);
            return variables.get(name);
        }
        if (outer != null)
            return outer.get(name);
        return null;
    }
}
