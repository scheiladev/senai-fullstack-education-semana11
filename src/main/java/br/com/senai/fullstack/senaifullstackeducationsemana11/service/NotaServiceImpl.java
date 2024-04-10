package br.com.senai.fullstack.senaifullstackeducationsemana11.service;

import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.NotaEntity;
import br.com.senai.fullstack.senaifullstackeducationsemana11.exception.NotFoundException;
import br.com.senai.fullstack.senaifullstackeducationsemana11.repository.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotaServiceImpl implements NotaService {

  private final NotaRepository repository;

  @Override
  public NotaEntity criar(NotaEntity entity) {
    entity.setId(null);
    return repository.save(entity);
  }

  @Override
  public List<NotaEntity> buscarTodos() {
    return repository.findAll();
  }

  @Override
  public NotaEntity buscarPorId(Long id) {
    return repository.findById(id).orElseThrow(() -> new NotFoundException("Nota não encontrada"));
  }

  @Override
  public NotaEntity alterar(Long id, NotaEntity entity) {
    buscarPorId(id);
    entity.setId(id);
    return repository.save(entity);
  }

  @Override
  public void apagar(Long id) {
    repository.delete(buscarPorId(id));
  }
}
