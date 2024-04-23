package br.com.clean.codechella.application.usecases;

import br.com.clean.codechella.application.gateways.RepositorioDeUsuario;

public class ExcluirUsuario {

    private final RepositorioDeUsuario repository;

    public ExcluirUsuario(RepositorioDeUsuario repository) {
        this.repository = repository;
    }

    public void excluirUsuario(String cpf) {
        repository.excluirUsuario(cpf);
    }

}
