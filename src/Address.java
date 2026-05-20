import exceptions.UnableToParseJsonException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Address {
    private String logradouro, bairro, localidade;

    public Address(){}
    public Address(String logradouro, String bairro, String localidade) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
    }
    //getters and setters
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    //static methods
    public static Address parseToAddress(String jsonString) {
        //recebe uma String advinda de JSON e converte para um objeto Address
        HashMap<String,String> fieldsMap = new HashMap<>();
        fieldsMap.put("logradouro",null);
        fieldsMap.put("bairro",null);
        fieldsMap.put("localidade",null);

        for (String field : fieldsMap.keySet()) {
            String regex = "\"" + field + "\"\\s*:\\s*\"([^\"]*)\"";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(jsonString);

            if (matcher.find()) {
                fieldsMap.put(field, matcher.group(1));
            } else {
                throw new UnableToParseJsonException("Erro ao tentar converter o Json para Address");
            }
        }

        String  logradouro = fieldsMap.get("logradouro"),
                bairro = fieldsMap.get("bairro"),
                localidade = fieldsMap.get("localidade");

        return new Address(logradouro,bairro,localidade);
    }









}
