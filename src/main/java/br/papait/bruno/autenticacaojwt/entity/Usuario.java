package br.papait.bruno.autenticacaojwt.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "LOGIN", unique = true)
  private String login;

  @Column(name = "PASSWORD", unique = true)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;
}
