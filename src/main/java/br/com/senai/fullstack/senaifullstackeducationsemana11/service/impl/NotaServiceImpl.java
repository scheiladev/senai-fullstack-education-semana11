package br.com.senai.fullstack.senaifullstackeducationsemana11.service.impl;

import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.AlterarNotaRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.request.IncluirNotaRequest;
import br.com.senai.fullstack.senaifullstackeducationsemana11.controller.dto.response.NotaResponse;
import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.CadernoEntity;
import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.NotaEntity;
import br.com.senai.fullstack.senaifullstackeducationsemana11.entity.UsuarioEntity;
import br.com.senai.fullstack.senaifullstackeducationsemana11.exception.customException.NotFoundException;
import br.com.senai.fullstack.senaifullstackeducationsemana11.exception.customException.RequisicaoInvalidaException;
import br.com.senai.fullstack.senaifullstackeducationsemana11.repository.CadernoRepository;
import br.com.senai.fullstack.senaifullstackeducationsemana11.repository.NotaRepository;
import br.com.senai.fullstack.senaifullstackeducationsemana11.repository.UsuarioRepository;
import br.com.senai.fullstack.senaifullstackeducationsemana11.service.NotaService;
import br.com.senai.fullstack.senaifullstackeducationsemana11.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotaServiceImpl implements NotaService {

  private final NotaRepository notaRepository;
  private final UsuarioRepository usuarioRepository;
  private final CadernoRepository cadernoRepository;
  private final TokenService tokenService;

  @Override
  public List<NotaResponse> buscarTodos(String token) {

    Long usuarioId = Long.valueOf(tokenService.buscarCampo(token, "sub"));

    List<NotaEntity> listaNotas = notaRepository.findAllByUsuarioId(usuarioId);

    if (listaNotas.isEmpty()) {
      throw new NotFoundException("Não há notas cadastradas.");
    }

    return listaNotas.stream()
      .map(nota -> new NotaResponse(
        nota.getId(),
        nota.getTitle(),
        nota.getContent(),
        nota.getCaderno().getId(),
        nota.getCaderno().getNome()))
      .collect(Collectors.toList());
  }

  @Override
  public NotaResponse buscarPorId(Long id, String token) {

    Long usuarioId = Long.valueOf(tokenService.buscarCampo(token, "sub"));

    NotaEntity nota = notaRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("Nota não encontrada"));

    if (!usuarioId.equals(nota.getUsuario().getId())) {
      throw new RequisicaoInvalidaException("Não é possível exibir notas de outro usuário.");
    }

    return new NotaResponse(
      nota.getId(),
      nota.getTitle(),
      nota.getContent(),
      nota.getCaderno().getId(),
      nota.getCaderno().getNome());
  }

  @Override
  public NotaResponse criar(IncluirNotaRequest request, String token) {

    if (request.title() == null) {
      throw new RequisicaoInvalidaException("Campo 'title' é obrigatório.");
    }

    if (request.content() == null) {
      throw new RequisicaoInvalidaException("Campo 'content' é obrigatório.");
    }

    if (request.cadernoId() == null) {
      throw new RequisicaoInvalidaException("Campo 'cadernoId' é obrigatório.");
    }

    Long usuarioId = Long.valueOf(tokenService.buscarCampo(token, "sub"));

    UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
      .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

    CadernoEntity caderno = cadernoRepository.findById(request.cadernoId())
      .orElseThrow(() -> new NotFoundException("Caderno não encontrado"));

    if (!usuarioId.equals(caderno.getUsuario().getId())) {
      throw new RequisicaoInvalidaException("Este caderno pertence a outro usuário.");
    }

    NotaEntity nota = new NotaEntity();
    nota.setTitle(request.title());
    nota.setContent(request.content());
    nota.setUsuario(usuario);
    nota.setCaderno(caderno);

    notaRepository.save(nota);

    return new NotaResponse(
      nota.getId(),
      nota.getTitle(),
      nota.getContent(),
      nota.getCaderno().getId(),
      nota.getCaderno().getNome());
  }

  @Override
  public NotaResponse alterar(Long id, AlterarNotaRequest request, String token) {

    Long usuarioId = Long.valueOf(tokenService.buscarCampo(token, "sub"));

    NotaEntity nota = notaRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("Nota não encontrada"));

    if (!usuarioId.equals(nota.getUsuario().getId())) {
      throw new RequisicaoInvalidaException("Esta nota pertence a outro usuário.");
    }

    if (request.title() != null && !nota.getTitle().equals(request.title())) {
      nota.setTitle(request.title());
    }

    if (request.content() != null && !nota.getContent().equals(request.content())) {
      nota.setContent(request.content());
    }

    if (request.cadernoId() != null && !nota.getCaderno().getId().equals(request.cadernoId())) {

      CadernoEntity caderno = cadernoRepository.findById(request.cadernoId())
        .orElseThrow(() -> new NotFoundException("Caderno não encontrado"));

      if (!usuarioId.equals(caderno.getUsuario().getId())) {
        throw new RequisicaoInvalidaException("Este caderno pertence a outro usuário.");
      }

      nota.setCaderno(caderno);
    }

    nota.setId(id);
    notaRepository.save(nota);

    return new NotaResponse(
      nota.getId(),
      nota.getTitle(),
      nota.getContent(),
      nota.getCaderno().getId(),
      nota.getCaderno().getNome());
  }

  @Override
  public void apagar(Long id, String token) {

    Long usuarioId = Long.valueOf(tokenService.buscarCampo(token, "sub"));

    NotaEntity nota = notaRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("Nota não encontrada"));

    if (!usuarioId.equals(nota.getUsuario().getId())) {
      throw new RequisicaoInvalidaException("Não é possível excluir notas de outro usuário.");
    }

    notaRepository.delete(nota);
  }
}
