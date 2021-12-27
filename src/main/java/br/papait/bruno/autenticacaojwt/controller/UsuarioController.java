package br.papait.bruno.autenticacaojwt.controller;

import br.papait.bruno.autenticacaojwt.entity.Usuario;
import br.papait.bruno.autenticacaojwt.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @GetMapping
  public ResponseEntity<List<Usuario>> findAll(){
    return ResponseEntity.ok(usuarioRepository.findAll());
  }

  @PostMapping
  public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
    return ResponseEntity.ok(usuarioRepository.save(usuario));
  }
}
