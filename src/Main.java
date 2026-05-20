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
            System.out.println("\nSelecione a opção desejada: \n" +
                    "0 - sair\n" +
                    "1 - buscar cep\n" +
                    "2 - buscar logradouro, bairro e localidade de um cep\n");
            userInput =s.nextLine();

            switch (userInput){
                case "0"://stop application
                    break;
                case "1"://look for all info for that CEP
                    System.out.println("\nDigite o CEP que deseja buscar ou 0 para sair: ");

                    userInput = s.nextLine();
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

                    break;
                case "2"://look for only Address info(logradouro, bairro e localidade)
                    System.out.println("\nDigite o CEP que deseja buscar ou 0 para sair: ");
                    userInput = s.nextLine();
                    if(!userInput.equals("0")){
                        try {
                            System.out.println(" ... Buscando informações... ");
                            Address address = requestHandler.getAddress(userInput);

                            System.out.printf("Sucesso na requisição.\n" +
                                            "---------------------\n" +
                                            "Logradouro: %s \n" +
                                            "Bairro: %s\n" +
                                            "Localidade: %s\n",
                                            address.getLogradouro(),
                                            address.getBairro(),
                                            address.getLocalidade() );


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
                    break;
                default:
                    System.out.println("Opção inválida. Por favor selecione uma opção válida!");

            }




            }
        }


}