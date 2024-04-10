package br.com.senai.fullstack.senaifullstackeducationsemana11.service;

import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.CadernoEntity;

import java.util.List;

public interface CadernoService {

  CadernoEntity criar(CadernoEntity entity);

  List<CadernoEntity> buscarTodos();

  CadernoEntity buscarPorId(Long id);

  CadernoEntity alterar(Long id, CadernoEntity entity);

  void apagar(Long id);
}
