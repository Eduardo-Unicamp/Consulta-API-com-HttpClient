package exceptions;

public class UnableToParseJsonException extends RuntimeException{
    String message;

    public UnableToParseJsonException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
