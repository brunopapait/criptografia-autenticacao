package br.papait.bruno.autenticacaojwt.data;

import br.papait.bruno.autenticacaojwt.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DetalhesUsuarioData implements UserDetails {

  private Optional<Usuario> usuario;

  public DetalhesUsuarioData(Optional<Usuario> usuario) {
    this.usuario = usuario;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new ArrayList<>();
  }

  @Override
  public String getPassword() {
    return usuario.orElse(new Usuario()).getPassword();
  }

  @Override
  public String getUsername() {
    return usuario.orElse(new Usuario()).getLogin();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
