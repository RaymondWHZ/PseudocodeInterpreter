package core.util;

public class SyntaxException extends RuntimeException {

    private Tokenizer.Navigator navigator;

    public SyntaxException(String message, Tokenizer tokenizer) {
        this(message, tokenizer.getCurrentNavigator());
    }

    public SyntaxException(String message, Tokenizer.Navigator navigator) {
        super(message);
        this.navigator = navigator;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + navigator.toString() + ")";
    }
}
