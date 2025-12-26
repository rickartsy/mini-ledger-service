package app.exception;

public class InvalidNameException extends AccountException {
    public InvalidNameException(String name) {
        super("Account name must not be null or blank. Provided: '" + name + "'");
    }
}
