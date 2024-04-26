package br.com.senai.fullstack.senaifullstackeducationsemana11.entity;

import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.LoginRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String login;
  private String senha;

  @ManyToOne
  private PerfilEntity perfil;

  public boolean senhaValida(
    LoginRequest loginRequest,
    BCryptPasswordEncoder bCryptEncoder
  ) {
    return bCryptEncoder.matches(
      loginRequest.senha(),
      this.senha
    );
    }
  }

