package exceptions;

public class CepInvalidoException extends RuntimeException{
    String message;

    public CepInvalidoException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
