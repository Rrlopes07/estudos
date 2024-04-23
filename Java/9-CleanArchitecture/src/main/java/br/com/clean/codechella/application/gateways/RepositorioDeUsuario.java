package br.com.clean.codechella.application.gateways;

import br.com.clean.codechella.domain.entities.usuario.Usuario;

import java.util.List;

public interface RepositorioDeUsuario {

    Usuario cadastrarUsuario(Usuario usuario);

    List<Usuario> listarTodos();

    Usuario alteraUsuario(String cpf, Usuario usuario);

    void excluirUsuario(String cpf);

}
