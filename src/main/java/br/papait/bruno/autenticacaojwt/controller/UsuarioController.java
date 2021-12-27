package br.papait.bruno.autenticacaojwt.controller;

import br.papait.bruno.autenticacaojwt.entity.Usuario;
import br.papait.bruno.autenticacaojwt.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @GetMapping
  public ResponseEntity<List<Usuario>> findAll(){
    return ResponseEntity.ok(usuarioRepository.findAll());
  }

  @PostMapping
  public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
    return ResponseEntity.ok(usuarioRepository.save(usuario));
  }

  @GetMapping(value = "/validar")
  public ResponseEntity<Boolean> validar(@RequestParam String login, @RequestParam String password) {


    Optional<Usuario> usuario = usuarioRepository.findByLogin(login);

    if (usuario.isEmpty()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
    }

    Boolean isValid = passwordEncoder.matches(password, usuario.get().getPassword());

    HttpStatus status = (isValid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

    return ResponseEntity.status(status).body(isValid);
  }
}
