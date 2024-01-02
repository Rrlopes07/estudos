package br.com.refatoracao.command.commands;

import br.com.refatoracao.command.Command;
import br.com.refatoracao.client.ClientHttpConfiguration;
import br.com.refatoracao.service.PetService;

import java.io.IOException;

public class ListarPetsCommand implements Command {

    @Override
    public void execute() {
        try {
            ClientHttpConfiguration client = new ClientHttpConfiguration();
            PetService petService = new PetService(client);

            petService.listarPets();
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
