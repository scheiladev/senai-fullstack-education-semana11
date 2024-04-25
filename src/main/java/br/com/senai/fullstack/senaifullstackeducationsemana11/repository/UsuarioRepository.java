package br.com.senai.fullstack.senaifullstackeducationsemana11.repository;

import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

  Optional<UsuarioEntity> findByLogin(String login);

}