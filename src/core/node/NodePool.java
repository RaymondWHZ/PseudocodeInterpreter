package core.node;

import java.util.HashMap;

/**
 * 存储了所有创建的变量和子程序的池
 */
class NodePool {

    private VariablePool variables = new VariablePool(null);
    private HashMap<String, ProcedureNode> procedures = new HashMap<>();
    private HashMap<String, FunctionNode> functions = new HashMap<>();

    public void setUpRange() {
        variables = new VariablePool(variables);
    }

    public void tearDownRange() {
        VariablePool outer = variables.outer;
        if (outer != null)
            variables = outer;
    }

    // 使用下列方法代理来避免从外界改变引用（注意不是避免改变值）

    public VariablePool getVariables() {
        return variables;
    }

    public HashMap<String, ProcedureNode> getProcedures() {
        return procedures;
    }

    public HashMap<String, FunctionNode> getFunctions() {
        return functions;
    }
}

/**
 * 以链表形式实现作用域区分的变量专用池
 */
class VariablePool extends HashMap<String, Object>{

    final VariablePool outer;

    public VariablePool(VariablePool outer) {
        this.outer = outer;
    }

    public Object get(String name) {
        if (containsKey(name)) {
            return super.get(name);
        }
        if (outer != null)
            return outer.get(name);
        return null;
    }
}
