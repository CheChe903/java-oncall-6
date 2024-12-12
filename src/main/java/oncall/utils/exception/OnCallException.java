package oncall.utils.exception;

public class OnCallException extends IllegalArgumentException {

    public OnCallException(ErrorMessage message) {
        super(message.toString());
    }
}
