package br.com.paulotrevizan.blogapi.services.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paulotrevizan.blogapi.domains.Postagem;
import br.com.paulotrevizan.blogapi.dto.response.ComentarioPostagemResponse;
import br.com.paulotrevizan.blogapi.dto.response.PostagemResponse;
import br.com.paulotrevizan.blogapi.dto.response.UsuarioResponse;

@Service
public class PostagemResponseMapper {

  @Autowired
  private ComentarioPostagemResponseMapper comentarioPostagemResponseMapper;

  public PostagemResponse domainToResponse(Postagem domain) {
    UsuarioResponse usuario = UsuarioResponse.builder()
            .id(domain.getUsuario().getId())
            .username(domain.getUsuario().getUsername())
            .nome(domain.getUsuario().getNome())
            .build();

    List<ComentarioPostagemResponse> comentarios = new ArrayList<>();
    if (Optional.ofNullable(domain.getComentarios()).isPresent()) {
      comentarios = Collections.synchronizedList(domain.getComentarios()
              .stream()
              .map(comentarioPostagemResponseMapper::domainToResponse)
              .collect(Collectors.toList()));
    }

    return PostagemResponse.builder()
            .id(domain.getId()).usuario(usuario)
            .titulo(domain.getTitulo())
            .conteudo(domain.getConteudo())
            .imagemCapa(domain.getImagemCapa())
            .data(domain.getData())
            .comentarios(comentarios)
            .build();
  }

}
