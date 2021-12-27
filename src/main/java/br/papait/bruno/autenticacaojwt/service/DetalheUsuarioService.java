package br.papait.bruno.autenticacaojwt.service;

import br.papait.bruno.autenticacaojwt.data.DetalhesUsuarioData;
import br.papait.bruno.autenticacaojwt.entity.Usuario;
import br.papait.bruno.autenticacaojwt.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuarioService implements UserDetailsService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<Usuario> usuario = usuarioRepository.findByLogin(username);

    if (usuario.isEmpty()) {
      throw new UsernameNotFoundException("Usuário " + username + " não encontrado.");
    }

    return new DetalhesUsuarioData(usuario);
  }
}
