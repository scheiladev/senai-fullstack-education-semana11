package br.com.senai.fullstack.senaifullstackeducationsemana11.repository;

import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {
  Optional<PerfilEntity> findByNome(String nome);
}
