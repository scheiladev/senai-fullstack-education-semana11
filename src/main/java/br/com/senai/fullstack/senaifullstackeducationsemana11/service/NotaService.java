package br.com.senai.fullstack.senaifullstackeducationsemana11.service;

import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.NotaEntity;

import java.util.List;

public interface NotaService {

  NotaEntity criar(NotaEntity entity);

  List<NotaEntity> buscarTodos();

  NotaEntity buscarPorId(Long id);

  NotaEntity alterar(Long id, NotaEntity entity);

  void apagar(Long id);
}
