package core.interpreter;

import core.node.ProgramNode;
import core.util.RunningException;
import core.util.SyntaxException;
import core.util.Tokenizer;

import java.util.ArrayList;

public class Interpreter {

    private static ProgramNode program;

    private static InputListener inputListener;
    private static ArrayList<OutputListener> outputListeners = new ArrayList<>();
    private static ArrayList<ExceptionListener> exceptionListeners = new ArrayList<>();

    /**
     * 解析传入的代码并保存解析完的节点树
     *
     * @param code 源代码
     */
    public static void interpret(String code) {
        Tokenizer tokenizer = new Tokenizer(code);
        try {
            program = new ProgramNode(tokenizer);
        } catch (SyntaxException e) {
            handleException(e);
        }
    }

    /**
     * 如果之前已经解析过，便可以直接运行
     */
    public static void execute() {
        if (program != null)
            try {
                program.execute();
            } catch (RunningException e) {
                handleException(e);
            }
    }

    /**
     * 挂载监听并提供程序输入的监听器，只能有一个
     *
     * @param listener 实例化的监听器
     */
    public static void setInputListener(InputListener listener) {
        inputListener = listener;
    }

    public static void removeInputListener() {
        setInputListener(null);
    }

    /**
     * 当程序需要得到输入的时候，调用此方法，会自动交给监听器代理
     */
    public static String input() {
        return inputListener.obtainInput();
    }


    /**
     * 挂载监听程序输出的监听器
     *
     * @param listener 实例化的监听器
     */
    public static void addOutputListener(OutputListener listener) {
        outputListeners.add(listener);
    }

    public static void removeOutputListener(OutputListener listener) {
        outputListeners.remove(listener);
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


    /**
     * 挂载监听程序异常的监听器，可能是Syntax和Running中的任何一者，需要自行判断
     *
     * @param listener 实例化的监听器
     */
    public static void addExceptionListener(ExceptionListener listener) {
        exceptionListeners.add(listener);
    }

    public static void removeExceptionListener(ExceptionListener listener) {
        exceptionListeners.remove(listener);
    }

    private static void handleException(Exception e) {
        for (ExceptionListener listener: exceptionListeners)
            listener.handleException(e);
    }
}
