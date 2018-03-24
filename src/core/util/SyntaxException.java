package core.util;

public class SyntaxException extends ProgramException {

    public SyntaxException(String message, Tokenizer tokenizer) {
        super(message, tokenizer.getCurrentNavigator());
    }
}
