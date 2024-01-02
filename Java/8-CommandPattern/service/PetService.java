package br.com.refatoracao.service;

import br.com.refatoracao.client.ClientHttpConfiguration;
import br.com.refatoracao.domain.pet.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PetService {

    private ClientHttpConfiguration client;
    private String uri = "http://localhost:8080/abrigos/";

    public PetService(ClientHttpConfiguration client) {
        this.client = client;
    }

    public void listarPets() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = new Scanner(System.in).nextLine();

        String uriRequisicao = uri + idOuNome + "/pets";
        HttpResponse<String> response = client.dispararRequisicaoGet(uriRequisicao);
        int statusCode = response.statusCode();
        if (statusCode == 404 || statusCode == 500)
            System.out.println("ID ou nome não cadastrado!");

        String responseBody = response.body();
        Pet[] abrigos = new ObjectMapper().readValue(responseBody, Pet[].class);
        List<Pet> petList = Arrays.stream(abrigos).toList();

        System.out.println("Pets cadastrados:");
        petList.forEach(System.out::println);
    }

    public void importarPets() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = new Scanner(System.in).nextLine();

        System.out.println("Digite o nome do arquivo CSV:");
        String nomeArquivo = new Scanner(System.in).nextLine();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));

            importarCsv(reader, idOuNome);

            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " +nomeArquivo);
        }
    }

    private void importarCsv(BufferedReader reader, String idOuNome) throws IOException, InterruptedException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] campos = line.split(",");
            String tipo = campos[0];
            String nome = campos[1];
            String raca = campos[2];
            int idade = Integer.parseInt(campos[3]);
            String cor = campos[4];
            Float peso = Float.parseFloat(campos[5]);

            Pet pet = new Pet(tipo, nome, raca, idade, cor, peso);

            String uriRequisicao = uri + idOuNome + "/pets";
            HttpResponse<String> response = client.dispararRequisicaoPost(uriRequisicao, pet);

            int statusCode = response.statusCode();
            String responseBody = response.body();
            if (statusCode == 200) {
                System.out.println("Pet cadastrado com sucesso: " + nome);
            } else if (statusCode == 404) {
                System.out.println("Id ou nome do abrigo não encontado!");
                break;
            } else if (statusCode == 400 || statusCode == 500) {
                System.out.println("Erro ao cadastrar o pet: " + nome);
                System.out.println(responseBody);
                break;
            }
        }
    }

}
