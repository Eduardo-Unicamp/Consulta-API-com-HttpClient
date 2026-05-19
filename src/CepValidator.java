import exceptions.CepInvalidoException;

public class CepValidator {
    public CepValidator(){}

    public static String ifValid(String cep){
        if(cep.strip().length()==8 && cep.strip().split(" ").length==1 ){
            return cep.strip();
        }else{
            throw new CepInvalidoException("O valor digitado não corresponde a um cep valido");
        }
    }
}
