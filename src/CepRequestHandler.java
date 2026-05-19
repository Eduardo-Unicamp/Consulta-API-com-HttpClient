import exceptions.CepInexistenteException;
import exceptions.CepInvalidoException;
import exceptions.RequisicaoMalSucedidaException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CepRequestHandler {
    private String baseUrl = "https://viacep.com.br/ws/";
    //NoArgsContructor
    public CepRequestHandler(){}
    //AllArgsConstructor
    public CepRequestHandler(String baseUrl){this.baseUrl=baseUrl;}



    //HTTP GET method
    public HttpResponse<String> getCEP(String cep){
        String url = baseUrl+"/"+CepValidator.ifValid(cep)+"/json/";

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //caso status 200 json com erro
            if(response.body().contains("\"erro\": \"true\"")){
                throw new CepInexistenteException("O cep informado não corresponde a um endereço!");
            }
            //caso erro 400(se por algum motivo a validação falhar)
            if(response.statusCode()>299){
                throw new RequisicaoMalSucedidaException(String.format(
                        "\nA requisição retornou um erro. Status: %d\n" +
                                "Tente conferir se o CEP foi digitado corretamente!",
                        response.statusCode()));
            }

            //se deu tudo certo
            return response;
            
        }catch(InterruptedException | IOException e){
           throw new RequisicaoMalSucedidaException("Ocorreu um erro na requisição");
        }
    }





}
