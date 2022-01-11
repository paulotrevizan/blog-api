package br.com.paulotrevizan.blogapi.services;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.paulotrevizan.blogapi.domains.Postagem;
import br.com.paulotrevizan.blogapi.domains.Usuario;
import br.com.paulotrevizan.blogapi.dto.PostagemRequest;
import br.com.paulotrevizan.blogapi.dto.response.PostagemResponse;
import br.com.paulotrevizan.blogapi.exceptions.NotFoundException;
import br.com.paulotrevizan.blogapi.repositories.PostagemRepository;
import br.com.paulotrevizan.blogapi.services.mapper.PostagemResponseMapper;

import lombok.RequiredArgsConstructor;

@Lazy
@Service
@RequiredArgsConstructor
public class PostagemService {

  private static final String NOT_FOUND = "Postagem não encontrada";

  private final PostagemRepository postagemRepository;
  private final UsuarioService usuarioService;
  private final PostagemResponseMapper postagemResponseMapper;

  public Postagem insert(final PostagemRequest request) {
    Usuario usuario = usuarioService.getUsuarioById(request.getUsuarioId());

    Postagem postagem = Postagem.builder()
            .usuario(usuario)
            .titulo(request.getTitulo())
            .conteudo(request.getConteudo())
            .imagemCapa(request.getImagemCapa())
            .data(LocalDate.now())
            .build();

    return postagemRepository.save(postagem);
  }

  public PostagemResponse getPostagemById(Integer id) {
    return postagemRepository.findById(id)
            .map(postagemResponseMapper::domainToResponse)
            .orElseThrow(() -> new NotFoundException(NOT_FOUND));
  }

  public void delete(Integer id) {
    PostagemResponse postagem = getPostagemById(id);
    postagemRepository.deleteById(postagem.getId());
  }

  public List<PostagemResponse> getAllPostagens(final Integer usuarioId) {
    List<Postagem> postagens = Objects.nonNull(usuarioId) ?
            Collections.synchronizedList(postagemRepository.findByUsuarioId(usuarioId)) :
            Collections.synchronizedList(postagemRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));

    return postagens.stream()
            .filter(Objects::nonNull)
            .map(postagemResponseMapper::domainToResponse)
            .collect(Collectors.toList());
  }

}
