package br.com.senai.fullstack.senaifullstackeducationsemana11.service;

import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.AlterarCadernoRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.IncluirCadernoRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.response.CadernoResponse;

import java.util.List;

public interface CadernoService {

  List<CadernoResponse> buscarTodos(String token);

  CadernoResponse buscarPorId(Long id, String token);

  CadernoResponse criar(IncluirCadernoRequest request, String token);

  CadernoResponse alterar(Long id, AlterarCadernoRequest request, String token);

  void apagar(Long id, String token);
}
