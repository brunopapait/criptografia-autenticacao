package br.papait.bruno.autenticacaojwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AutenticacaoJwtApplication {

  public static void main(String[] args) {
    SpringApplication.run(AutenticacaoJwtApplication.class, args);
  }

}
