package br.com.clean.codechella.config;

import br.com.clean.codechella.application.gateways.RepositorioDeUsuario;
import br.com.clean.codechella.application.usecases.AlterarUsuario;
import br.com.clean.codechella.application.usecases.CriarUsuario;
import br.com.clean.codechella.application.usecases.ExcluirUsuario;
import br.com.clean.codechella.application.usecases.ListarUsuarios;
import br.com.clean.codechella.infra.gateways.RepositorioDeUsuarioJpa;
import br.com.clean.codechella.infra.gateways.UsuarioEntityMapper;
import br.com.clean.codechella.infra.persistency.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {

    @Bean
    CriarUsuario criarUsuario(RepositorioDeUsuario repository) {
        return new CriarUsuario(repository);
    }

    @Bean
    AlterarUsuario alteraUsuario(RepositorioDeUsuario repository) {
        return new AlterarUsuario(repository);
    }

    @Bean
    ListarUsuarios listarUsuarios(RepositorioDeUsuario repository) {
        return new ListarUsuarios(repository);
    }

    @Bean
    ExcluirUsuario excluirUsuarios(RepositorioDeUsuario repository) { return new ExcluirUsuario(repository); }

    @Bean
    RepositorioDeUsuarioJpa criarRepositorio(UsuarioRepository repository, UsuarioEntityMapper mapper) {
        return new RepositorioDeUsuarioJpa(repository, mapper);
    }

    @Bean
    UsuarioEntityMapper criarMapper() {
        return new UsuarioEntityMapper();
    }

}
