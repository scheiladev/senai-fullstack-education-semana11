package br.com.senai.fullstack.senaifullstackeducationsemana11.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="perfil")
public class PerfilEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String nome;
}

