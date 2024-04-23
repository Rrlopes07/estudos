package br.com.clean.codechella.infra.persistency;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByCpf(String cpf);

}
