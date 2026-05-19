package exceptions;

public class CepInexistenteException extends RuntimeException{
    String message;

    public CepInexistenteException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
