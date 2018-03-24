package core.util;

public class RunningException extends ProgramException {

    public RunningException(Exception e, Tokenizer.Navigator navigator) {
        super(e.getMessage(), navigator);
    }
}
