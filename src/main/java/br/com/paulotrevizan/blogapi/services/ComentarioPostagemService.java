package br.com.paulotrevizan.blogapi.services;

import java.time.LocalDate;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.paulotrevizan.blogapi.domains.ComentarioPostagem;
import br.com.paulotrevizan.blogapi.domains.Postagem;
import br.com.paulotrevizan.blogapi.domains.Usuario;
import br.com.paulotrevizan.blogapi.dto.ComentarioPostagemRequest;
import br.com.paulotrevizan.blogapi.dto.response.ComentarioPostagemResponse;
import br.com.paulotrevizan.blogapi.dto.response.PostagemResponse;
import br.com.paulotrevizan.blogapi.exceptions.NotFoundException;
import br.com.paulotrevizan.blogapi.repositories.ComentarioPostagemRepository;
import br.com.paulotrevizan.blogapi.services.mapper.ComentarioPostagemResponseMapper;

import lombok.RequiredArgsConstructor;

@Lazy
@Service
@RequiredArgsConstructor
public class ComentarioPostagemService {

  private static final String NOT_FOUND = "Comentário não encontrado";

  private final ComentarioPostagemRepository comentarioPostagemRepository;
  private final UsuarioService usuarioService;
  private final PostagemService postagemService;
  private final ComentarioPostagemResponseMapper comentarioPostagemResponseMapper;

  public ComentarioPostagem insert(final Integer postagemId, final ComentarioPostagemRequest request) {
    PostagemResponse postagem = postagemService.getPostagemById(postagemId);

    Usuario usuario = usuarioService.getUsuarioById(request.getUsuarioId());

    ComentarioPostagem comentario = ComentarioPostagem.builder()
            .postagem(Postagem.builder().id(postagem.getId()).build())
            .usuario(usuario)
            .conteudo(request.getConteudo())
            .data(LocalDate.now())
            .build();

    return comentarioPostagemRepository.save(comentario);
  }

  public ComentarioPostagemResponse getComentarioPostagemById(final Integer postagemId, final Integer id) {
    return comentarioPostagemRepository.findByPostagemIdAndId(postagemId, id)
            .map(comentarioPostagemResponseMapper::domainToResponse)
            .orElseThrow(() -> new NotFoundException(NOT_FOUND));
  }

  public void delete(final Integer postagemId, final Integer id) {
    ComentarioPostagemResponse comentario = getComentarioPostagemById(postagemId, id);
    comentarioPostagemRepository.deleteById(comentario.getId());
  }

}
