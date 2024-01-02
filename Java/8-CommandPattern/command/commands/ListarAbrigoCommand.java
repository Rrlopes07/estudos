package br.com.refatoracao.command.commands;

import br.com.refatoracao.command.Command;
import br.com.refatoracao.client.ClientHttpConfiguration;
import br.com.refatoracao.service.AbrigoService;

import java.io.IOException;

public class ListarAbrigoCommand implements Command {

    @Override
    public void execute() {
        try {
            ClientHttpConfiguration client = new ClientHttpConfiguration();
            AbrigoService abrigoService = new AbrigoService(client);

            abrigoService.listarAbrigos();
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
