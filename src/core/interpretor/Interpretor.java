package core.interpretor;

import core.node.ProgramNode;
import core.util.Tokenizer;

import java.util.ArrayList;

public class Interpretor {

    private static ProgramNode program;

    private static ArrayList<OutputListener> outputListeners = new ArrayList<>();

    public static void interprete(String code) {
        Tokenizer tokenizer = new Tokenizer(code);
        program = new ProgramNode(tokenizer);
    }

    public static void execute() {
        if (program != null)
            program.execute();
    }

    public static void addOutputListener(OutputListener listener) {
        outputListeners.add(listener);
    }

    public static void output(String text) {
        for (OutputListener listener: outputListeners)
            listener.outputOccur(text);
    }
}
