package br.com.senai.fullstack.senaifullstackeducationsemana11.service;

import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.AlterarNotaRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.IncluirNotaRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.response.NotaResponse;

import java.util.List;

public interface NotaService {

  List<NotaResponse> buscarTodos(String token);

  NotaResponse buscarPorId(Long id, String token);

  NotaResponse criar(IncluirNotaRequest request, String token);

  NotaResponse alterar(Long id, AlterarNotaRequest request, String token);

  void apagar(Long id, String token);
}
