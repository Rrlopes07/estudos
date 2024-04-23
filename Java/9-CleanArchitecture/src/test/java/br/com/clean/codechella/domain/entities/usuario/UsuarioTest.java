package br.com.clean.codechella.domain.entities.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UsuarioTest {

    @Test
    public void naoDeveCadastrarUsuarioComCpfInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario("123456789-99", "Joao", LocalDate.parse("1990-08-01"), "email@email.com"));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario("12345678999", "Joao", LocalDate.parse("1990-08-01"), "email@email.com"));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario("", "Joao", LocalDate.parse("1990-08-01"), "email@email.com"));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario(null, "Joao", LocalDate.parse("1990-08-01"), "email@email.com"));
    }

    @Test
    public void deveCriarUsuarioUsandoFabricaDeUsuario() {
        FabricaDeUsuario fabrica = new FabricaDeUsuario();
        Usuario usuario = fabrica.comNomeCpfNascimento("123.456.789-87", "Joao",
                LocalDate.parse("1990-08-01"));
        usuario = fabrica.incluiEndereco("81546-748", 99, "543");

        Assertions.assertEquals("Joao", usuario.getNome());
        Assertions.assertEquals("543", usuario.getEndereco().getComplemento());
    }

    @Test
    public void naoDeveCadastrarUsuarioComMenosDe18Anos() {
        LocalDate dataNascimento = LocalDate.now().minusYears(17);

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Usuario(null, "Joao", dataNascimento, "email@email.com");
        });

        Assertions.assertEquals("Usuário deve ter pelo menos 18 anos de idade!", exception.getMessage());
    }

}
