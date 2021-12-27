package br.papait.bruno.autenticacaojwt.repository;

import br.papait.bruno.autenticacaojwt.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Optional<Usuario> findByLogin(String login);
}
