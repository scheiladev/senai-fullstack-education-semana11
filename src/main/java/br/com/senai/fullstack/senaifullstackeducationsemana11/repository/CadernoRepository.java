package br.com.senai.fullstack.senaifullstackeducationsemana11.repository;

import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.CadernoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CadernoRepository extends JpaRepository<CadernoEntity, Long> {
  List<CadernoEntity> findAllByUsuarioId(Long id);
}
