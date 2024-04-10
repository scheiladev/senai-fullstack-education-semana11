package br.com.senai.fullstack.senaifullstackeducationsemana11.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "nota")
public class NotaEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @ManyToOne(optional = false)
  @JoinColumn(name = "caderno_id", nullable = false)
  private CadernoEntity caderno;

  @ManyToOne(optional = false)
  @JoinColumn(name = "usuario_id", nullable = false)
  private UsuarioEntity usuario;



}
