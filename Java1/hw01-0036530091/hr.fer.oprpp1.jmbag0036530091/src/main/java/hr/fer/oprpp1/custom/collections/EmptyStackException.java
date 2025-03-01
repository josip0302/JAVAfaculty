package hr.fer.oprpp1.custom.collections;

public class EmptyStackException extends  RuntimeException {
    String message;

    public EmptyStackException() {
    }

    public EmptyStackException(String message) {
        this.message = message;
    }

    public EmptyStackException(String message, String message1) {
        super(message);
        this.message = message1;
    }
    public String toString() {
        return ("Custom Exception Occurred : " + message);
    }
}
