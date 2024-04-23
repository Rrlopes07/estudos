package br.com.clean.codechella.infra.gateways;

import br.com.clean.codechella.application.gateways.RepositorioDeUsuario;
import br.com.clean.codechella.domain.entities.usuario.Usuario;
import br.com.clean.codechella.infra.persistency.UsuarioEntity;
import br.com.clean.codechella.infra.persistency.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDeUsuarioJpa implements RepositorioDeUsuario {

    private final UsuarioRepository repository;
    private final UsuarioEntityMapper mapper;

    public RepositorioDeUsuarioJpa(UsuarioRepository repository, UsuarioEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        UsuarioEntity entity = mapper.toEntity(usuario);
        repository.save(entity);

        return mapper.toDomain(entity);
    }

    @Override
    public List<Usuario> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Usuario alteraUsuario(String cpf, Usuario usuario) {
        UsuarioEntity entity = repository.findByCpf(cpf);

        if (entity != null) {
            var atualizado = mapper.toEntity(usuario);
            atualizado.setId(entity.getId());
            repository.save(atualizado);
            return mapper.toDomain(atualizado);
        }

        throw new IllegalArgumentException("Usuário não encontrado!");
    }

    @Override
    public void excluirUsuario(String cpf) {
        UsuarioEntity entity = repository.findByCpf(cpf);
        repository.deleteById(entity.getId());
    }

}
