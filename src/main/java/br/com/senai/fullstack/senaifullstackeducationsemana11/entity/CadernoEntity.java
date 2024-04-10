package br.com.senai.fullstack.senaifullstackeducationsemana11.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "caderno")
public class CadernoEntity implements Serializable  {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @ManyToOne(optional = false)
  @JoinColumn(name = "usuario_id", nullable = false)
  private UsuarioEntity usuario;
}
