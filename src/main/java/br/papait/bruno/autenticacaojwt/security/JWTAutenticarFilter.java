package br.papait.bruno.autenticacaojwt.security;

import br.papait.bruno.autenticacaojwt.data.DetalhesUsuarioData;
import br.papait.bruno.autenticacaojwt.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {

  public static final int TOKEN_EXPIRACAO = 600_000;
  public static final String TOKEN_SENHA = "f01b08f3-4763-40a2-8ada-b4495b4ea93d";

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      Usuario usuario= new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

      return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getPassword(), new ArrayList<>()));

    } catch (IOException e) {
      throw new RuntimeException("Falha ao autenticar o usuário. " + e.getMessage());
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    DetalhesUsuarioData detalhesUsuarioData = (DetalhesUsuarioData)authResult.getPrincipal();

    String token = JWT.create()
            .withSubject(detalhesUsuarioData.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
            .sign(Algorithm.HMAC512(TOKEN_SENHA));

    response.getWriter().write(token);
    response.getWriter().flush();
  }
}
