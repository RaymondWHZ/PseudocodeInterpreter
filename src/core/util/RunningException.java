package core.util;

public class RunningException extends RuntimeException {

    private final int lineNumber;

    public RunningException(Exception e, int lineNumber) {
        this(e.getMessage(), lineNumber);
    }

    public RunningException(String message, int lineNumber) {
        super(message);
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return super.toString() + " (line: " + lineNumber + ")";
    }
}
