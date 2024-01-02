package br.com.refatoracao;

import br.com.refatoracao.command.CommandExecutor;
import br.com.refatoracao.command.commands.CadastrarAbrigoCommand;
import br.com.refatoracao.command.commands.ImportarPetsCommand;
import br.com.refatoracao.command.commands.ListarAbrigoCommand;
import br.com.refatoracao.command.commands.ListarPetsCommand;

import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {
        CommandExecutor executor = new CommandExecutor();

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");

        int opcaoEscolhida = 0;
        while (opcaoEscolhida != 5) {
            System.out.println("""
                    DIGITE O NÚMERO DA OPERAÇÃO DESEJADA:
                    1 -> Listar abrigos cadastrados
                    2 -> Cadastrar novo abrigo
                    3 -> Listar pets do abrigo
                    4 -> Importar pets do abrigo
                    5 -> Sair
                    """);

            String textoDigitado = new Scanner(System.in).nextLine();
            opcaoEscolhida = Integer.parseInt(textoDigitado);

            switch (opcaoEscolhida) {
                case 1 -> executor.executeCommand(new ListarAbrigoCommand());
                case 2 -> executor.executeCommand((new CadastrarAbrigoCommand()));
                case 3 -> executor.executeCommand((new ListarPetsCommand()));
                case 4 -> executor.executeCommand(new ImportarPetsCommand());
                case 5 -> System.exit(0);
                default -> System.out.println("NÚMERO INVÁLIDO!");
            }
        }
        System.out.println("Finalizando o programa...");
    }

}
