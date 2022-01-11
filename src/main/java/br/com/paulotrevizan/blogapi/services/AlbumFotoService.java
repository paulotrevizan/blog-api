package br.com.paulotrevizan.blogapi.services;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.paulotrevizan.blogapi.domains.AlbumFoto;
import br.com.paulotrevizan.blogapi.domains.Usuario;
import br.com.paulotrevizan.blogapi.dto.AlbumFotoRequest;
import br.com.paulotrevizan.blogapi.dto.response.AlbumFotoResponse;
import br.com.paulotrevizan.blogapi.exceptions.NotFoundException;
import br.com.paulotrevizan.blogapi.repositories.AlbumFotoRepository;
import br.com.paulotrevizan.blogapi.services.mapper.AlbumFotoResponseMapper;

import lombok.RequiredArgsConstructor;

@Lazy
@Service
@RequiredArgsConstructor
public class AlbumFotoService {

  private static final String NOT_FOUND = "Álbum de fotos não encontrado";

  private final AlbumFotoRepository albumFotoRepository;
  private final UsuarioService usuarioService;
  private final AlbumFotoResponseMapper albumFotoResponseMapper;

  public AlbumFoto insert(final AlbumFotoRequest request) {
    Usuario usuario = usuarioService.getUsuarioById(request.getUsuarioId());

    AlbumFoto albumFoto = AlbumFoto.builder()
            .usuario(usuario)
            .nome(request.getNome())
            .build();

    return albumFotoRepository.save(albumFoto);
  }

  public AlbumFotoResponse getAlbumFotoById(final Integer id) {
    return albumFotoRepository.findById(id)
            .map(albumFotoResponseMapper::domainToResponse)
            .orElseThrow(() -> new NotFoundException(NOT_FOUND));
  }

  public void delete(final Integer id) {
    AlbumFotoResponse albumFoto = getAlbumFotoById(id);
    albumFotoRepository.deleteById(albumFoto.getId());
  }

  public List<AlbumFotoResponse> getAllAlbuns(final Integer usuarioId) {
    List<AlbumFoto> albuns =
        Objects.nonNull(usuarioId) ?
                Collections.synchronizedList(albumFotoRepository.findByUsuarioId(usuarioId)) :
                Collections.synchronizedList(albumFotoRepository.findAll());

    return albuns.stream()
            .filter(Objects::nonNull)
            .map(albumFotoResponseMapper::domainToResponse)
            .collect(Collectors.toList());
  }

}
