package br.com.clean.codechella.application.usecases;

import br.com.clean.codechella.application.gateways.RepositorioDeUsuario;
import br.com.clean.codechella.domain.entities.usuario.Usuario;

import java.util.List;

public class ListarUsuarios {

    private final RepositorioDeUsuario repository;

    public ListarUsuarios(RepositorioDeUsuario repository) {
        this.repository = repository;
    }

    public List<Usuario> obterTodosUsuarios() {
        return repository.listarTodos();
    }

}
