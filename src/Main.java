import core.interpretor.Interpretor;

public class Main {

    public static void main(String[] args) {
        String code =
                "OUTPUT \"Hello world!!!\"\n" +
                "OUTPUT \"I'm Psuedocode!!!\"\n" +
                "OUTPUT \"Nice to meet you!!!\"\n";

        Interpretor.interprete(code);
        Interpretor.addOutputListener((System.out::println));
        Interpretor.execute();
    }
}
