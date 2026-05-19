import exceptions.CepInexistenteException;
import exceptions.CepInvalidoException;
import exceptions.RequisicaoMalSucedidaException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       CepRequestHandler requestHandler = new CepRequestHandler();
        Scanner s = new Scanner(System.in);
        String userInput = "";

        while (!userInput.equals("0")){
            System.out.println("\nDigite o CEP que deseja buscar ou 0 para sair: ");
            userInput =s.nextLine();

            if(!userInput.equals("0")){
                try {
                    System.out.println(" ... Buscando informações... ");
                    HttpResponse<String> response = requestHandler.getCEP(userInput);

                    System.out.printf("Sucesso na requisição.\n" +
                            "---------------------\n" +
                            " Status:%s\n" +
                            "---------------------" +
                                    "%s",
                            response.statusCode(),response.body());


                }catch(CepInvalidoException e) {
                    System.out.println(e.getMessage());
                }catch (RequisicaoMalSucedidaException e){
                    System.out.println(e.getMessage());
                }catch(CepInexistenteException e){
                    System.out.println(e.getMessage());
                }
            }else{
                System.out.println("Encerrando aplicação...");
                break;
            }

            }
        }


}