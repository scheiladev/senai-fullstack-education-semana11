package br.com.senai.fullstack.senaifullstackeducationsemana11.service.impl;

import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.CadernoEntity;
import br.com.senai.fullstack.senaifullstackeducationsemana11.exception.NotFoundException;
import br.com.senai.fullstack.senaifullstackeducationsemana11.repository.CadernoRepository;
import br.com.senai.fullstack.senaifullstackeducationsemana11.service.CadernoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CadernoServiceImpl implements CadernoService {

  private final CadernoRepository repository;

  @Override
  public CadernoEntity criar(CadernoEntity entity) {
    entity.setId(null);
    return repository.save(entity);
  }

  @Override
  public List<CadernoEntity> buscarTodos() {
    return repository.findAll();
  }

  @Override
  public CadernoEntity buscarPorId(Long id) {
    return repository.findById(id).orElseThrow(() -> new NotFoundException("Caderno não encontrado"));
  }

  @Override
  public CadernoEntity alterar(Long id, CadernoEntity entity) {
    buscarPorId(id);
    entity.setId(id);
    return repository.save(entity);
  }

  @Override
  public void apagar(Long id) {
    repository.delete(buscarPorId(id));
  }
}
