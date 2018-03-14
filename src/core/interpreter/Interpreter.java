package core.interpreter;

import core.node.ProgramNode;
import core.util.Tokenizer;

import java.util.ArrayList;

public class Interpreter {

    private static ProgramNode program;

    private static InputListener inputListener;
    private static ArrayList<OutputListener> outputListeners = new ArrayList<>();

    /**
     * 解析传入的代码并保存解析完的节点树
     *
     * @param code 源代码
     */
    public static void interpret(String code) {
        Tokenizer tokenizer = new Tokenizer(code);
        program = new ProgramNode(tokenizer);
    }

    /**
     * 如果之前已经解析过，便可以直接运行
     */
    public static void execute() {
        if (program != null)
            program.execute();
    }

    public static void setInputListener(InputListener listener) {
        inputListener = listener;
    }

    /**
     * 挂载监听程序输出的监听器
     *
     * @param listener 实例化的监听器
     */
    public static void addOutputListener(OutputListener listener) {
        outputListeners.add(listener);
    }

    public static String input() {
        return inputListener.obtainInput();
    }

    /**
     * 当程序需要输出的时候，调用此方法，会自动交给监听器代理
     *
     * @param text 输出的文本
     */
    public static void output(String text) {
        for (OutputListener listener: outputListeners)
            listener.outputOccur(text);
    }
}
