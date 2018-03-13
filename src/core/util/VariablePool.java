package core.util;

import java.util.HashMap;

public class VariablePool {

    public VariablePool outer;

    private final HashMap<String, Object> variables = new HashMap<>();

    public void set(String name, Object value) {
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
