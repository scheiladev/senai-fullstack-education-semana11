package br.com.senai.fullstack.senaifullstackeducationsemana11.repository;

import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.CadernoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadernoRepository extends JpaRepository<CadernoEntity, Long> {
}
