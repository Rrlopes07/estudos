package br.com.refatoracao.command.commands;

import br.com.refatoracao.command.Command;
import br.com.refatoracao.client.ClientHttpConfiguration;
import br.com.refatoracao.service.AbrigoService;

import java.io.IOException;

public class CadastrarAbrigoCommand implements Command {

    @Override
    public void execute() {
        try {
            ClientHttpConfiguration client = new ClientHttpConfiguration();
            AbrigoService abrigoService = new AbrigoService(client);

            abrigoService.cadastrarAbrigo();
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
