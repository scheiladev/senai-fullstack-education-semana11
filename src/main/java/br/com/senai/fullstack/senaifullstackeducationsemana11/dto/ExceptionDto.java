package br.com.senai.fullstack.senaifullstackeducationsemana11.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDto {
  private String codigo;
  private String mensagem;
}