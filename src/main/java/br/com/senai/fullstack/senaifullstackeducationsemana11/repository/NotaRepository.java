package br.com.senai.fullstack.senaifullstackeducationsemana11.repository;

import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
  List<NotaEntity> findAllByUsuarioId(Long id);
}
