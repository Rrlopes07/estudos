package br.com.clean.codechella.application.usecases;

import br.com.clean.codechella.application.gateways.RepositorioDeUsuario;
import br.com.clean.codechella.domain.entities.usuario.Usuario;

public class AlterarUsuario {

    private final RepositorioDeUsuario repository;

    public AlterarUsuario(RepositorioDeUsuario repository) {
        this.repository = repository;
    }

    public Usuario alteraDadosUsuario(String cpf, Usuario usuario) {
        return repository.alteraUsuario(cpf, usuario);
    }

}
