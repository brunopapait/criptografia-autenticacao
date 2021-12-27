package br.papait.bruno.autenticacaojwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncodersConfiguration {

  @Bean
  public PasswordEncoder getPasswordEncodes() {
    return new BCryptPasswordEncoder();
  }
}
