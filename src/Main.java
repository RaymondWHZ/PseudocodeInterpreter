import core.interpreter.Interpreter;

public class Main {

    public static void main(String[] args) {
        String code =
                "OUTPUT \"Hello world!!!\"\n" +
                "OUTPUT \"I'm Psuedocode!!!\"\n" +
                "OUTPUT \"Nice to meet you!!!\"\n";

        Interpreter.interpret(code);  // 调用解释器解析代码

        Interpreter.addOutputListener(System.out::println);
        // 这行把命令行输出方法的引用作为监听器传给解释器

        Interpreter.execute();  // 发出执行命令
    }
}
