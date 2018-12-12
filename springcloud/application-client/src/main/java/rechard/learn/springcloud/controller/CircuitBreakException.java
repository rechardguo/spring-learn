package rechard.learn.springcloud.controller;

public class CircuitBreakException extends Exception {
    public CircuitBreakException(String message, Throwable cause) {
        super(message, cause);
    }

    public CircuitBreakException(String message) {
        super(message);
    }
}
