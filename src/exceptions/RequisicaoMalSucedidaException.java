package exceptions;

public class RequisicaoMalSucedidaException extends RuntimeException{
    String message;

    public RequisicaoMalSucedidaException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
