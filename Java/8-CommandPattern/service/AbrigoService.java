package br.com.refatoracao.service;

import br.com.refatoracao.client.ClientHttpConfiguration;
import br.com.refatoracao.domain.abrigo.Abrigo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AbrigoService {

    private ClientHttpConfiguration client;
    private String uri = "http://localhost:8080/abrigos";

    public AbrigoService(ClientHttpConfiguration client) {
        this.client = client;
    }

    public void listarAbrigos() throws IOException, InterruptedException {
        HttpResponse<String> response = client.dispararRequisicaoGet(uri);
        String responseBody = response.body();
        Abrigo[] abrigos = new ObjectMapper().readValue(responseBody, Abrigo[].class);
        List<Abrigo> abrigoList = Arrays.stream(abrigos).toList();

        System.out.println("Abrigos cadastrados:");
        abrigoList.forEach(System.out::println);
    }

    public void cadastrarAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o nome do abrigo:");
        String nome = new Scanner(System.in).nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = new Scanner(System.in).nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = new Scanner(System.in).nextLine();

        Abrigo abrigo = new Abrigo(nome, telefone, email);

        HttpResponse<String> response = client.dispararRequisicaoPost(uri, abrigo);

        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }
    }

}
