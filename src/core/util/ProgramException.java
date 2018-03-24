package core.util;

public class ProgramException extends RuntimeException {

    private Tokenizer.Navigator navigator;

    public ProgramException(String message, Tokenizer.Navigator navigator) {
        super(message);
        this.navigator = navigator;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + navigator.toString() + ")";
    }
}
