package br.com.clean.codechella.infra.controller;

import br.com.clean.codechella.application.usecases.AlterarUsuario;
import br.com.clean.codechella.application.usecases.CriarUsuario;
import br.com.clean.codechella.application.usecases.ExcluirUsuario;
import br.com.clean.codechella.application.usecases.ListarUsuarios;
import br.com.clean.codechella.domain.entities.usuario.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CriarUsuario criarUsuario;
    private final AlterarUsuario alteraUsuario;
    private final ListarUsuarios listarUsuarios;
    private final ExcluirUsuario excluirUsuario;

    public UsuarioController(CriarUsuario criarUsuario, AlterarUsuario alteraUsuario, ListarUsuarios listarUsuarios, ExcluirUsuario excluirUsuario) {
        this.criarUsuario = criarUsuario;
        this.alteraUsuario = alteraUsuario;
        this.listarUsuarios = listarUsuarios;
        this.excluirUsuario = excluirUsuario;
    }

    @GetMapping
    public List<UsuarioDto> listarUsuarios() {
        return listarUsuarios.obterTodosUsuarios()
                .stream()
                .map(usuario -> new UsuarioDto(usuario.getCpf(), usuario.getNome(), usuario.getNascimento(), usuario.getEmail()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public UsuarioDto cadastrarUsuario(@RequestBody UsuarioDto dto) {
        Usuario salvo = criarUsuario.cadastrarUsuario(
                new Usuario(dto.cpf(), dto.nome(), dto.nascimento(), dto.email())
        );

        return new UsuarioDto(salvo.getCpf(), salvo.getNome(), salvo.getNascimento(), salvo.getEmail());
    }

    @PutMapping("/{cpf}")
    public UsuarioDto atualizarUsuario(@PathVariable String cpf, @RequestBody UsuarioDto dto) {
        Usuario atualizado = alteraUsuario.alteraDadosUsuario(cpf,
                new Usuario(dto.cpf(), dto.nome(), dto.nascimento(), dto.email()));

        return new UsuarioDto(atualizado.getCpf(), atualizado.getNome(), atualizado.getNascimento(), atualizado.getEmail());
    }

    @DeleteMapping("/{cpf}")
    public void excluirUsuario(@PathVariable String cpf) {
        excluirUsuario.excluirUsuario(cpf);
    }

}
