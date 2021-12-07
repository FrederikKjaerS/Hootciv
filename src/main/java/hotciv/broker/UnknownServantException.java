package hotciv.broker;

public class UnknownServantException extends RuntimeException {
    public UnknownServantException(String message) {
        super(message);
    }

    public UnknownServantException(String message, Throwable cause) {
        super(message, cause);
    }
}