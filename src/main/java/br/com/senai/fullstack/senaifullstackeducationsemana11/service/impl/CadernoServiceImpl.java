package br.com.senai.fullstack.senaifullstackeducationsemana11.service.impl;

import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.AlterarCadernoRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.IncluirCadernoRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.response.CadernoResponse;
import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.CadernoEntity;
import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.UsuarioEntity;
import br.com.senai.fullstack.senaifullstackeducationsemana11.exception.customException.NotFoundException;
import br.com.senai.fullstack.senaifullstackeducationsemana11.exception.customException.RequisicaoInvalidaException;
import br.com.senai.fullstack.senaifullstackeducationsemana11.repository.CadernoRepository;
import br.com.senai.fullstack.senaifullstackeducationsemana11.repository.UsuarioRepository;
import br.com.senai.fullstack.senaifullstackeducationsemana11.service.CadernoService;
import br.com.senai.fullstack.senaifullstackeducationsemana11.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CadernoServiceImpl implements CadernoService {

  private final CadernoRepository cadernoRepository;
  private final UsuarioRepository usuarioRepository;
  private final TokenService tokenService;

  @Override
  public List<CadernoResponse> buscarTodos(String token) {

    Long usuarioId = Long.valueOf(tokenService.buscarCampo(token, "sub"));

    List<CadernoEntity> listaCadernos = cadernoRepository.findAllByUsuarioId(usuarioId);

    if (listaCadernos.isEmpty()) {
      throw new NotFoundException("Não há cadernos cadastrados.");
    }

    return listaCadernos.stream()
      .map(caderno -> new CadernoResponse(
        caderno.getId(),
        caderno.getNome()))
      .collect(Collectors.toList());
  }

  @Override
  public CadernoResponse buscarPorId(Long id, String token) {

    Long usuarioId = Long.valueOf(tokenService.buscarCampo(token, "sub"));

    CadernoEntity caderno = cadernoRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("Caderno não encontrado"));

    if (!usuarioId.equals(caderno.getUsuario().getId())) {
      throw new RequisicaoInvalidaException("Não é possível exibir cadernos de outro usuário.");
    }

    return new CadernoResponse(
      caderno.getId(),
      caderno.getNome());
  }

  @Override
  public CadernoResponse criar(IncluirCadernoRequest request, String token) {

    if (request.nome() == null) {
      throw new RequisicaoInvalidaException("Campo 'nome' é obrigatório.");
    }

    Long usuarioId = Long.valueOf(tokenService.buscarCampo(token, "sub"));

    UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
      .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

    CadernoEntity caderno = new CadernoEntity();
    caderno.setNome(request.nome());
    caderno.setUsuario(usuario);

    cadernoRepository.save(caderno);

    return new CadernoResponse(
      caderno.getId(),
      caderno.getNome());
  }

  @Override
  public CadernoResponse alterar(Long id, AlterarCadernoRequest request, String token) {

    Long usuarioId = Long.valueOf(tokenService.buscarCampo(token, "sub"));

    CadernoEntity caderno = cadernoRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("Caderno não encontrado"));

    if (!usuarioId.equals(caderno.getUsuario().getId())) {
      throw new RequisicaoInvalidaException("Este caderno pertence a outro usuário.");
    }

    if (request.nome() != null && !caderno.getNome().equals(request.nome())) {
      caderno.setNome(request.nome());
    }

    caderno.setId(id);
    cadernoRepository.save(caderno);

    return new CadernoResponse(
      caderno.getId(),
      caderno.getNome());
  }

  @Override
  public void apagar(Long id, String token) {

    Long usuarioId = Long.valueOf(tokenService.buscarCampo(token, "sub"));

    CadernoEntity caderno = cadernoRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("Caderno não encontrado"));

    if (!usuarioId.equals(caderno.getUsuario().getId())) {
      throw new RequisicaoInvalidaException("Não é possível excluir cadernos de outro usuário.");
    }

    cadernoRepository.delete(caderno);
  }
}
